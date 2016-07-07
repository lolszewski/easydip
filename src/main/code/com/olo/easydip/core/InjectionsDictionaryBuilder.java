package code.com.olo.easydip.core;

import code.com.olo.easydip.annotations.AutoInject;
import code.com.olo.easydip.annotations.SkipAutoInject;
import code.com.olo.easydip.model.Injection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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

        for (String key: map.keySet()){
            Object implementation = map.get(key);
            injectForFields(implementation.getClass(), implementation, map);
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

    public String getKeyOrNull(Class<?> abstractionClass, String namedImplementation, HashMap<String, Object> map){
        String className = abstractionClass.toString();

        String keyBeginning = namedImplementation;
        keyBeginning += className;

        for (String key: map.keySet()){
            if (key.startsWith(keyBeginning)){
                return key;
            }
        }

        return null;
    }

    private void injectForFields(Class<?> abstractionClass, Object instance, HashMap<String, Object> map) throws IllegalAccessException {
        Annotation annotation = abstractionClass.getAnnotation(AutoInject.class);
        if (annotation != null){
            Field[] fields = abstractionClass.getDeclaredFields();
            for (Field field: fields){
                Annotation skipAnnotation = field.getAnnotation(SkipAutoInject.class);
                if (skipAnnotation == null){
                    String implementationName = "";

                    AutoInject namedInjectAnnotation = field.getAnnotation(AutoInject.class);
                    if (namedInjectAnnotation != null){
                        implementationName = namedInjectAnnotation.implementationName();
                    }

                    String keyOrNull = getKeyOrNull(field.getType(), implementationName, map);
                    if (keyOrNull != null){
                        field.setAccessible(true);
                        field.set(instance, map.get(keyOrNull));
                    }
                }
            }
        }
    }
}