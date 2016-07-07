package code.com.olo.easydip.core;

import code.com.olo.easydip.model.Injection;
import code.com.olo.easydip.reflections.InjectionsInstancesBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InjectionsDictionaryBuilder {
    public static InjectionsDictionaryBuilder instance = new InjectionsDictionaryBuilder();

    public HashMap<String, Object> buildInjectionsMap(ArrayList<Injection> injections) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Injection injection: injections){
            String key = getInjectionKey(injection);

            Class<?> c =  Class.forName(((Class<?>)injection.implementation).getName());
            Object instance = InjectionsInstancesBuilder.instance.getInstance(c);

            map.put(key, instance);
        }

        return map;
    }

    private String getInjectionKey(Injection injection){
        String key = new String();

        key = injection.implementationName;
        key += injection.abstraction.toString();
        key += injection.implementation.toString();

        return key;
    }
}