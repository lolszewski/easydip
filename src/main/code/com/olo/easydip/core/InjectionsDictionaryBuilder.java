package code.com.olo.easydip.core;

import code.com.olo.easydip.model.Injection;

import java.util.ArrayList;
import java.util.HashMap;

public class InjectionsDictionaryBuilder {
    public static InjectionsDictionaryBuilder instance = new InjectionsDictionaryBuilder();

    public HashMap<String, Object> buildInjectionsMap(ArrayList<Injection> injections) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for (Injection injection: injections){
            String key = getInjectionKey(injection);

            Class<?> classDefinition =  Class.forName(((Class<?>)injection.implementation).getName());
            Object instance = InjectionsInstancesBuilder.instance.getInstance(classDefinition);

            map.put(key, instance);
        }

        return map;
    }

    public String getInjectionKey(Injection injection){
        String key = new String();

        key = injection.implementationName;
        key += injection.abstraction.toString();
        key += injection.implementation.toString();

        return key;
    }
}