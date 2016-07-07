package com.olo;

import code.com.olo.easydip.core.InjectionsManager;
import com.olo.mocks.implementations.AbstractComplexed;
import com.olo.mocks.implementations.SimpleAbstract;
import com.olo.mocks.interfaces.ISimpleService;
import junit.framework.TestCase;

public class InjectionsManagerTests extends TestCase {
    private final String namedImplementation = "named simple service implementation";

    public void testInjectionsManagerShouldBeInitializedProperly() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
    }

    public void testInjectionsManagerShouldGetInterfaceSimpleImplementation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        ISimpleService service = InjectionsManager.instance.get(ISimpleService.class);

        assertNotNull(service);
        assertNotSame("", service.SimpleMethod());
    }

    public void testInjectionsManagerShouldGetAbstractSimpleImplementation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        SimpleAbstract service = InjectionsManager.instance.get(SimpleAbstract.class);

        assertNotNull(service);
        assertNotSame("", service.SimpleAbstractMethod());
    }

    public void testInjectionsManagerShouldGetAbstractComplexImplementation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        AbstractComplexed service = InjectionsManager.instance.get(AbstractComplexed.class);

        assertNotNull(service);
        assertNotSame("", service.SimpleAbstractMethod());
    }

    public void testInjectionsManagerShouldGetNamedInterfaceSimpleImplementation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        ISimpleService service = InjectionsManager.instance.get(ISimpleService.class, namedImplementation);

        assertNotNull(service);
        assertNotSame("", service.SimpleMethod());
    }

    public void testInjectionsManagerShouldGetNamedAbstractSimpleImplementation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        SimpleAbstract service = InjectionsManager.instance.get(SimpleAbstract.class, namedImplementation);

        assertNotNull(service);
        assertNotSame("", service.SimpleAbstractMethod());
    }

    public void testInjectionsManagerShouldGetNamedAbstractComplexImplementation() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        AbstractComplexed service = InjectionsManager.instance.get(AbstractComplexed.class, namedImplementation);

        assertNotNull(service);
        assertNotSame("", service.SimpleAbstractMethod());
    }
}