package com.example.prot_1.view.osm;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.prot_1.R;
import com.example.prot_1.view.main.MainActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.modules.ArchiveFileFactory;
import org.osmdroid.tileprovider.modules.IArchiveFile;
import org.osmdroid.tileprovider.modules.OfflineTileProvider;
import org.osmdroid.tileprovider.tilesource.FileBasedTileSource;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.io.File;
import java.util.Set;

public class OSMFragment extends Fragment {
    //private static final String TAG = "NetworkFragment";
    private Button btnNavFragMessage;
    private Button btnNavFragNetwork;

    public static final String TAG = "MAPVIEW";
    MapView mMapView = null;
    TextView tvText;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.osmmap_layout, container, false);
        tvText = view.findViewById(R.id.tvOSMText);
        btnNavFragMessage = view.findViewById(R.id.btnNavMessage);
        btnNavFragNetwork = view.findViewById(R.id.btnNavNetwork);

        btnNavFragMessage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Going to Fragment1", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(0);
            }
        });

        btnNavFragNetwork.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Going to Fragment2", Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).setViewPager(1);
            }
        });

        //handle permissions first, before map is created. not depicted here

        //load/initialize the osmdroid configuration, this can be done
        Context ctx = (MainActivity)getActivity();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        //setting this before the layout is inflated is a good idea
        //it 'should' ensure that the map has a writable location for the map cache, even without permissions
        //if no tiles are displayed, you can try overriding the cache path using Configuration.getInstance().setCachePath
        //see also StorageUtils
        //note, the load method also sets the HTTP User Agent to your application's package name, abusing osm's tile servers will get you banned based on this string

        //inflate and create the map
        //setContentView(R.layout.activity_main);

        mMapView = (MapView) view.findViewById(R.id.map);
        mapSource();
        mMapView.setTileSource(TileSourceFactory.MAPNIK);
        mMapView.setMultiTouchControls(true);
        IMapController mapController = mMapView.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(52.522381, 13.409701);
        mapController.setCenter(startPoint);

        return view;
    }
    public void onResume(){
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        mMapView.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    public void onPause(){
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        mMapView.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    public void mapSource() {
        //not even needed since we are using the offline tile provider only

        ////////////////////////////////////////////////////////////////
        //toggle internet connection
        //this.mMapView.setUseDataConnection(false);

        //https://github.com/osmdroid/osmdroid/issues/330
        //custom image placeholder for files that aren't available
        //////mMapView.getTileProvider().setTileLoadFailureImage(getResources().getDrawable(R.drawable.notfound));


        //first we'll look at the default location for tiles that we support
        //File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/osmdroid/tiles/");
//        File f = new File("/data/data/com.example.test_osmdroid/files/osmdroid/tiles");
        //File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download");
        File f = new File("/data/data/com.example.prot_1/files/osmdroid/tiles");
        this.tvText.setText("file created");

        if (f.exists()) {
            tvText.setText("folder exists");

            File[] list = f.listFiles();
            if(list == null) tvText.setText("found nothing in list");;
            if (list != null) {
                for (int i = 0; i < list.length; i++) {
                    if (list[i].isDirectory()) {
                        tvText.setText("found something in list");
                        continue;
                    }
                    String name = list[i].getName().toLowerCase();
                    if (!name.contains(".")) {
                        continue; //skip files without an extension
                    }
                    name = name.substring(name.lastIndexOf(".") + 1);
                    if (name.length() == 0) {
                        continue;
                    }
                    tvText.setText("bevore is extension registered");

                    if (ArchiveFileFactory.isFileExtensionRegistered(name)) {
                        //Toast.makeText((MainActivity)getActivity(), "found " + name, Toast.LENGTH_LONG).show();
                        try {

                            //ok found a file we support and have a driver for the format, for this demo, we'll just use the first one

                            //create the offline tile provider, it will only do offline file archives
                            //again using the first file
                            OfflineTileProvider tileProvider = new OfflineTileProvider(new SimpleRegisterReceiver((MainActivity)getActivity()),
                                    new File[]{list[i]});

                            //tell osmdroid to use that provider instead of the default rig which is (asserts, cache, files/archives, online
                            mMapView.setTileProvider(tileProvider);

                            //this bit enables us to find out what tiles sources are available. note, that this action may take some time to run
                            //and should be ran asynchronously. we've put it inline for simplicity

                            String source = "";
                            IArchiveFile[] archives = tileProvider.getArchives();
                            if (archives.length > 0) {
                                //cheating a bit here, get the first archive file and ask for the tile sources names it contains
                                Set<String> tileSources = archives[0].getTileSources();
                                //presumably, this would be a great place to tell your users which tiles sources are available
                                if (!tileSources.isEmpty()) {
                                    //ok good, we found at least one tile source, create a basic file based tile source using that name
                                    //and set it. If we don't set it, osmdroid will attempt to use the default source, which is "MAPNIK",
                                    //which probably won't match your offline tile source, unless it's MAPNIK
                                    source = tileSources.iterator().next();
                                    this.mMapView.setTileSource(FileBasedTileSource.getSource(source));
                                } else {
                                    this.mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                                }

                            } else {
                                this.mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
                            }

                            //Snackbar.make(getView(), "Using " + list[i].getAbsolutePath() + " " + source, Snackbar.LENGTH_SHORT).show();
                            this.mMapView.invalidate();
                            return;
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            tvText.setText("test");

            //Toast.makeText((MainActivity)getActivity(), f.getAbsolutePath() + " did not have any files I can open! Try using MOBAC", Toast.LENGTH_LONG).show();
        } else {
            tvText.setText("no folder found");

            //Toast.makeText((MainActivity)getActivity(), f.getAbsolutePath() + " dir not found!", Toast.LENGTH_LONG).show();
        }

    }
}
