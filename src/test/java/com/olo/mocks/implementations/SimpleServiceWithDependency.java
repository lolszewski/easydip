package com.olo.mocks.implementations;

import code.com.olo.easydip.annotations.AutoInject;
import code.com.olo.easydip.annotations.Implementation;
import com.olo.mocks.interfaces.ISimpleService;

@AutoInject
@Implementation(implementationName = "simple dependency")
public class SimpleServiceWithDependency {
    public ISimpleService service;
}
