package code.com.olo.easydip.core;

import code.com.olo.easydip.annotations.Implementation;
import code.com.olo.easydip.model.Injection;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.Set;

public class InjectionsDetector {
    public static InjectionsDetector instance = new InjectionsDetector();

    public ArrayList<Injection> detectInjections(){
        ArrayList<Injection> injections = new ArrayList<Injection>();

        Package[] packages = Package.getPackages();
        for (Package programPackage: packages){
            String packageName = programPackage.getName();

            Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage(packageName)));
            Set<Class<?>> implementations = reflections.getTypesAnnotatedWith(Implementation.class);

            if (implementations.toArray().length > 0){
                for (Class<?> implementation: implementations){
                    String implementationName = implementation.getAnnotation(Implementation.class).implementationName();

                    addRecursively(implementation, implementation, implementationName, injections);

                    Class<?>[] abstractions = implementation.getInterfaces();
                    for (Class<?> abstraction: abstractions){
                        addRecursively(implementation, abstraction, implementationName, injections);
                    }
                    
                    Class<?> superClass = implementation.getSuperclass();
                    if (superClass != null){
                        addRecursively(implementation, superClass, implementationName, injections);
                    }
                }
            }
        }

        return injections;
    }

    private void addRecursively(Class<?> implementation, Class<?> abstraction, String implementationName, ArrayList<Injection> injections) {
        for (Injection injection : injections) {
            if (injection.implementation == implementation &&
                    injection.abstraction == abstraction &&
                    implementationName == implementationName) {
                return;
            }
        }

        Injection injection = new Injection(implementationName, abstraction, implementation);
        injections.add(injection);

        Class<?> abstractionAbstraction = abstraction.getSuperclass();
        if (abstractionAbstraction != null){
            addRecursively(implementation, abstractionAbstraction, implementationName, injections);
        }
    }
}
