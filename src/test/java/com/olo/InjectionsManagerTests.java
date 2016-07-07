package com.olo;

import code.com.olo.easydip.core.InjectionsManager;
import com.olo.mocks.implementations.*;
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

    public void testInjectionsManagerShouldInjectDependencyAutomatically() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        SimpleServiceWithDependency simpleService = InjectionsManager.instance.get(SimpleServiceWithDependency.class, "simple dependency");

        assertNotNull(simpleService);
        assertNotNull(simpleService.service);
    }

    public void testInjectionsManagerShouldNotInjectDependencyAutomaticallyIfSkippingIsAnnotated() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();
        SimpleServiceWithDependencySkipping simpleService = InjectionsManager.instance.get(SimpleServiceWithDependencySkipping.class, "simple dependency with skipping");

        assertNotNull(simpleService);
        assertNotNull(simpleService.service);
        assertNull(simpleService.serviceForSkip);
    }

    public void testPerformance() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000; i++) {
            ISimpleService isimpleService = InjectionsManager.instance.get(ISimpleService.class);
            SimpleService simpleService = InjectionsManager.instance.get(SimpleService.class);
            SimpleAbstract simpleAbstract = InjectionsManager.instance.get(SimpleAbstract.class);
            SimpleServiceNamed simpleServiceNamed = InjectionsManager.instance.get(SimpleServiceNamed.class, "named simple service implementation");
            AbstractComplexed abstractComplexed = InjectionsManager.instance.get(AbstractComplexed.class);
            SimpleServiceWithDependency simpleServiceWithDependency = InjectionsManager.instance.get(SimpleServiceWithDependency.class, "simple dependency");
            SimpleServiceWithDependencySkipping simpleServiceWithDependencySkipping = InjectionsManager.instance.get(SimpleServiceWithDependencySkipping.class, "simple dependency with skipping");

            if (isimpleService == null) {
                throw new NullPointerException("isimpleService is null");
            }
            if (simpleService == null) {
                throw new NullPointerException("simpleService is null");
            }
            if (simpleAbstract == null) {
                throw new NullPointerException("simpleAbstract is null");
            }
            if (simpleServiceNamed == null) {
                throw new NullPointerException("simpleServiceNamed is null");
            }
            if (abstractComplexed == null) {
                throw new NullPointerException("abstractComplexed is null");
            }
            if (simpleServiceWithDependency == null) {
                throw new NullPointerException("simpleServiceWithDependency is null");
            }
            if (simpleServiceWithDependencySkipping == null) {
                throw new NullPointerException("simpleServiceWithDependencySkipping is null");
            }
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        
        assertSame(false, elapsedTime > 10000);
    }
}