package com.olo.mocks.implementations;

import code.com.olo.easydip.annotations.AutoInject;
import com.olo.mocks.interfaces.ISimpleService;

public class SimpleServiceWithDependency {
    @AutoInject
    private ISimpleService service;
}
