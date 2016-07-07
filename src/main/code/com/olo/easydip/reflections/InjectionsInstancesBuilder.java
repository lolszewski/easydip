package code.com.olo.easydip.reflections;

public class InjectionsInstancesBuilder {
    public static InjectionsInstancesBuilder instance = new InjectionsInstancesBuilder();

    public <T extends Object> T getInstance(Class classType)
            throws IllegalAccessException, InstantiationException {
        T instance = (T)classType.newInstance();

        return instance;
    }
}
