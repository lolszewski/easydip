package com.olo.mocks.implementations;

import code.com.olo.easydip.annotations.AutoInject;
import code.com.olo.easydip.annotations.Implementation;
import code.com.olo.easydip.annotations.SkipAutoInject;
import com.olo.mocks.interfaces.ISimpleService;

@AutoInject
@Implementation(implementationName = "simple dependency with skipping")
public class SimpleServiceWithDependencySkipping {
    public ISimpleService service;

    @SkipAutoInject
    public ISimpleService serviceForSkip;
}
