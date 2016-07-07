package com.olo;

import code.com.olo.easydip.core.InjectionsDictionaryBuilder;
import code.com.olo.easydip.model.Injection;
import code.com.olo.easydip.core.InjectionsDetector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.HashMap;

public class InjectionsInstancesBuilderTests extends TestCase {
    public void testShouldBuildAtLeastMockImplementations() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ArrayList<Injection> injections = InjectionsDetector.instance.detectInjections();
        HashMap<String, Object> injectionsMap = InjectionsDictionaryBuilder.instance.buildInjectionsMap(injections);

        assertEquals(false, injectionsMap.isEmpty());
    }
}