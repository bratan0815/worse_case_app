package com.example.prot_1;


import com.example.prot_1.view.messages.MessagesFragmentTest;
import com.example.prot_1.view.messages.ViewMessageActivityTest;
import com.example.prot_1.view.messages.WriteMessageActivityTest;
import com.example.prot_1.view.network.NetworkFragmentTest;
import com.example.prot_1.view.osm.OSMFragmentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        MessagesFragmentTest.class,
        NetworkFragmentTest.class,
        OSMFragmentTest.class,
        ViewMessageActivityTest.class,
        WriteMessageActivityTest.class
        })
public class UITestSuite {
}
