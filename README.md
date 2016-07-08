This is one of the projects that was created in education purposes (I'm currently .net developer and trying to migrate into java :)).

EasyDip is a simple way for supporting DIP (Dependency inversion principle, Dependency injection).
It has been implemented with decorator pattern approach.

Example of usage has been pushed in other repository:
https://github.com/lolszewski/easydip-usage

There are few annotations that EasyDip uses:

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Implementation {
    public String implementationName() default "";
}
```

This annotation is for marking classes that can be injected and available in EasyDip mechanism (InjectionsManager -> get).
This annotation can be set on types only. 

```java
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoInject {
    public String implementationName() default "";
}
```

This annotation tells EasyDip to automatically inject all implementations in class body.
This annotation can be set on types and fields. 
If this annotation is set on some class, all available implementations will be injected for fields of matched types.

Example:
Let's imagine we have some interface:

```java
public interface IClientDataAccess {
    List<Client> getClients();
}
```

and some implementation of it with EasyDip annotation (Implementation):

```java
@Implementation
public class SomeClientDataAccess implements IClientDataAccess {
    public List<Client> getClients() {
        return null;
    }
}
```

In this approach you will be able to get implementation of this service by fallowing expression:

```java
InjectionsManager.instance.initialize();
IClientDataAccess clientDataAccess = InjectionsManager.instance.get(IClientDataAccess.class);
```

In above implementation EasyDip will automatically match proper implementation for IClientDataAccess interface, which is SomeClientDataAccess during rutime.
Having this we can go further and try to complicate EasyDip usage a little:

Let's have IClientDataAccess interface. 
Let's have FakeClientsService, which is the source of our fake data.

```java
@Implementation
public class FakeClientsService {
    public ArrayList<Client> getFakeClients(){
        ArrayList<Client> clients = new ArrayList<Client>();

        clients.add(new Client("Mark", 18));
        clients.add(new Client("Andrew", 24));
        clients.add(new Client("Donald", 70));
        clients.add(new Client("Anthony", 26));
        clients.add(new Client("Gregory", 30));
        clients.add(new Client("Martin", 46));
        clients.add(new Client("Ann", 23));
        clients.add(new Client("Monica", 79));
        clients.add(new Client("Jennifer", 63));

        return clients;
    }
}
```

As I said we still have our interface:
```java
public interface IClientDataAccess {
    List<Client> getClients();
}
```

and its 3 implementations:

```java
@Implementation(implementationName = "mssql")
@AutoInject
public class MsSqlClientDataAccess implements IClientDataAccess {

    FakeClientsService fakeClientsService;

    public List<Client> getClients() {
        return fakeClientsService.getFakeClients();
    }
}
```

```java
@Implementation(implementationName = "oracle")
@AutoInject
public class OracleClientDataAccess implements IClientDataAccess {
    FakeClientsService fakeClientsService;

    public List<Client> getClients() {

        return fakeClientsService.getFakeClients().subList(0, 2);
    }
}
```

```java
@Implementation(implementationName = "elastic")
@AutoInject
public class ElasticClientsDataAccess implements IClientDataAccess {
    FakeClientsService fakeClientsService;

    public List<Client> getClients() {
        return fakeClientsService.getFakeClients().subList(0, 6);
    }
}
```

As you can see all of those implementations have @Implementation(implementationName = "...") set.
You have also be aware of our FakeClientsService fakeClientsService, because it's never initialized explicitly in those implementations, but somehow this object is not null - all is available thanks to EasyDip and its @AutoInject annotation :);
This is really important and in EasyDip nomenclature it is something called "named implementation".
With this approach we can do something like this:

```java

    public static void main( String[] args ) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        InjectionsManager.instance.initialize();

        showClients("mssql");
        showClients("oracle");
        showClients("elastic");
    }
    
    private static void showClients(String source){
        List<Client> clients = InjectionsManager.instance.get(IClientDataAccess.class, source).getClients();
        printClients(clients, source);
    }

    private static void printClients(List<Client> clients, String source){
        System.out.println(String.format("Clients from %1$s (count:%2$d)", source, clients.toArray().length));
        for (Client client: clients){
            System.out.println(String.format("Name: %1$s, Age: %2$d", client.Name, client.Age));
        }
        System.out.println("--------------------------");
    }
```

If you have any question, feel free to contact me via gmail (lsolszewski@gmail.com).
I encourage you to take a look at the usage example project pushed in other repository:
https://github.com/lolszewski/easydip-usage

Enjoy!
