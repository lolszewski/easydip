package com.olo.mocks.implementations;

import code.com.olo.easydip.annotations.Implementation;
import com.olo.mocks.interfaces.ISimpleService;

@Implementation(implementationName = "named simple service implementation")
public class SimpleServiceNamed  extends AbstractComplexed implements ISimpleService {
    public String SimpleMethod() {
        return "SimpleService";
    }
}