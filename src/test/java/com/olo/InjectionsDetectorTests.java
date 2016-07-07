package com.olo;

import code.com.olo.easydip.model.Injection;
import code.com.olo.easydip.reflections.InjectionsDetector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

public class InjectionsDetectorTests extends TestCase {
    public InjectionsDetectorTests( String testName ){
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( InjectionsDetectorTests.class );
    }

    public void testShouldDetectAtLeastMockImplementations(){
        ArrayList<Injection> injections = InjectionsDetector.instance.detectInjections();
        assertNotSame(0, injections.toArray().length);
    }
}