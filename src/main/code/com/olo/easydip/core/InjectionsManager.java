package code.com.olo.easydip.core;

import code.com.olo.easydip.annotations.AutoInject;
import code.com.olo.easydip.annotations.SkipAutoInject;
import code.com.olo.easydip.model.Injection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class InjectionsManager {
    public static InjectionsManager instance = new InjectionsManager();

    private HashMap<String, Object> injectionsMap;

    public void initialize() throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        ArrayList<Injection> injectionsList = InjectionsDetector.instance.detectInjections();
        injectionsMap = InjectionsDictionaryBuilder.instance.buildInjectionsMap(injectionsList);

    }

    public <T> T get(Class<T> abstractionClass) {
        return get(abstractionClass, "");
    }

    public <T> T get(Class<T> abstractionClass, String namedImplementation) {
        String implementationKey = InjectionsDictionaryBuilder.instance.getKeyOrNull(abstractionClass, namedImplementation, injectionsMap);

        if (implementationKey != null){
            return (T)injectionsMap.get(implementationKey);
        }

        String className = abstractionClass.toString();
        throw new NoSuchElementException("There was no implementations found for " + className + ". Are you sure you not missing @implementation annotation?");
    }
}
