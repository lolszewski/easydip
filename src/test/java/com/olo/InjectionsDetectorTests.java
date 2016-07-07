package com.olo;

import code.com.olo.easydip.model.Injection;
import code.com.olo.easydip.reflections.InjectionsDetector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

/**
 * Created by U6028107 on 7/7/2016.
 */
public class InjectionsDetectorTests extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public InjectionsDetectorTests( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( InjectionsDetectorTests.class );
    }

    public void testInjectionsDetectorShouldDetectAtLeastMockImplementations(){
        ArrayList<Injection> injections = InjectionsDetector.instance.detectInjections();
        assertNotSame(0, injections.toArray().length);
    }
}