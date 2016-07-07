package code.com.olo;

import code.com.olo.easydip.model.Injection;
import code.com.olo.easydip.reflections.InjectionsDetector;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ArrayList<Injection> injections = InjectionsDetector.instance.detectInjections();
        System.out.println("Injections: " + injections.toArray().length);
    }
}