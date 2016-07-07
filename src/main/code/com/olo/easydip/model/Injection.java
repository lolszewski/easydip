package code.com.olo.easydip.model;

public class Injection {
    public String implementationName;
    public Object abstraction;
    public Object implementation;

    public Injection(String implementationName, Object abstraction, Object implementation){
        this.implementationName = implementationName;
        this.abstraction = abstraction;
        this.implementation = implementation;
    }
}