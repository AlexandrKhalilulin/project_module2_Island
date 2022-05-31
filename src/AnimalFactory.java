import java.util.Map;
import java.util.function.Supplier;

public class AnimalFactory {
    Map<Class, Supplier<Animal>> animals = Map.ofEntries(
            Map.entry(Wolf.class, Wolf::new),
            Map.entry(Horse.class, Horse::new),
            Map.entry(Stag.class, Stag::new),
            Map.entry(Rabbit.class, Rabbit::new),
            Map.entry(Boa.class, Boa::new),
            Map.entry(Fox.class, Fox::new),
            Map.entry(Caterpillar.class, Caterpillar::new),
            Map.entry(Eagle.class, Eagle::new),
            Map.entry(Mouse.class, Mouse::new),
            Map.entry(Bear.class, Bear::new),
            Map.entry(Goat.class, Goat::new),
            Map.entry(Sheep.class, Sheep::new),
            Map.entry(Boar.class, Boar::new),
            Map.entry(Buffalo.class, Buffalo::new),
            Map.entry(Duck.class, Duck::new)
    );

    public Animal createInstance(Class clazz) {
        Supplier<Animal> supplier = animals.get(clazz);
        return supplier.get();
    }
}
