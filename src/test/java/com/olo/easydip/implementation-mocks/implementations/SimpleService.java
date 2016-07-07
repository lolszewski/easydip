package code.com.olo.easydip.samples.implementations;

import code.com.olo.easydip.annotations.Implementation;

@Implementation
public class SimpleService extends AbstractComplexed implements ISimpleService {
    public String SimpleMethod() {
        return "SimpleService";
    }
}
