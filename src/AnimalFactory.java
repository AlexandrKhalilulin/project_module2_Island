import java.util.Map;
import java.util.function.Supplier;

public class AnimalFactory {
    Map<Class, Supplier<Animal>> animals = Map.ofEntries(
           Map.entry(Wolf.class, Wolf::new),
           Map.entry(Horse.class, Horse::new),
           Map.entry(Stag.class, Stag::new)
    );

    public Animal createInstance (Class clazz){
        Supplier<Animal> supplier = animals.get(clazz);
        return supplier.get();
    }
}
