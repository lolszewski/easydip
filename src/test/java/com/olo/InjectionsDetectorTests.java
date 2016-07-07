package com.olo;

import code.com.olo.easydip.model.Injection;
import code.com.olo.easydip.core.InjectionsDetector;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;

public class InjectionsDetectorTests extends TestCase {
    public void testShouldDetectAtLeastMockImplementations(){
        ArrayList<Injection> injections = InjectionsDetector.instance.detectInjections();
        assertNotSame(0, injections.toArray().length);
    }
}