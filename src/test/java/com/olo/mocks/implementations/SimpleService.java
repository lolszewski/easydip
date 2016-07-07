package com.olo.mocks.implementations;

import code.com.olo.easydip.annotations.Implementation;
import com.olo.mocks.interfaces.*;

@Implementation
public class SimpleService extends AbstractComplexed implements ISimpleService {
    public String SimpleMethod() {
        return "SimpleService";
    }
}