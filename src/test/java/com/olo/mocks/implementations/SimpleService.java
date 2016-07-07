package java.com.olo.mocks.implementations;

import code.com.olo.easydip.annotations.Implementation;
import java.com.olo.mocks.interfaces.ISimpleService;

@Implementation
public class SimpleService extends AbstractComplexed implements ISimpleService {
    public String SimpleMethod() {
        return "SimpleService";
    }
}