import java.util.*;
import java.util.stream.Collectors;

public class Cell implements Runnable {
    private Set<Animal> animals = new HashSet<>();
    private Set<Plant> plants = new HashSet<>();
    private final int MAX_PLANTS = 200;
    private final int lengthAddress;
    private final int heightAddress;
    private static int number_each_species = 4;
    private final int SATIETY_REDUCTION_PERCENTAGE = 100;

    public Set<Animal> getAnimals() {
        return animals;
    }

    public Cell(int lengthAddress, int heightAddress) {
        this.lengthAddress = lengthAddress;
        this.heightAddress = heightAddress;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    @Override
    public void run() {
        resetSatiety();
        growingUpPlants();
        reproducePlantsInCell();
        //sendingAnimalsToEat();
        //reproduceAnimalsInCell();

        animalsCycle();
    }

    private void animalsCycle() {
        Set<Animal> offspringSet = new HashSet<>();

        for (Animal animal: animals
             ) {

            animal.eat(this);

            HashSet<? extends Entity> reproduce = animal.reproduce();
            offspringSet.addAll((Collection<? extends Animal>) reproduce);

        }

        animals.addAll(offspringSet);

    }

    private void reproduceAnimalsInCell() {
       // Set<Animal> offspringSet = animals.stream().flatMap(animal -> ((Collection<? extends Animal>) animal.reproduce()).stream()).collect(Collectors.toSet());
        Set<Animal> offspringSet = new HashSet<>();
        for (Animal animal : animals) {
            HashSet<? extends Entity> reproduce = animal.reproduce();
            offspringSet.addAll((Collection<? extends Animal>) reproduce);
        }


        // Set<Animal> offspringSet = animals.stream().peek(Animal::reproduce).collect(Collectors.toSet());

        animals.addAll(offspringSet);
    }

    private void sendingAnimalsToEat() {
        System.out.println("start eating");
        animals.forEach(animal -> animal.eat(this));
        System.out.println("end eating");
    }

    private void resetSatiety() {
        animals = animals.stream().peek(s -> s.setSatiety(s.getSatiety() - s.getSatiety() / 100 * SATIETY_REDUCTION_PERCENTAGE)).collect(Collectors.toSet());
    }

    private void reproducePlantsInCell() {
        HashSet<Plant> newer = plants.stream().map(Plant::reproduce).flatMap(Collection::stream).collect(Collectors.toCollection(HashSet::new));
        Iterator<Plant> iterator = newer.iterator();
        while (iterator.hasNext() && plants.size() < MAX_PLANTS) plants.add(iterator.next());
    }


    private void growingUpPlants() {
        plants = plants.stream().peek(Grow::grow).collect(Collectors.toSet());
    }

    public void fillingCell() {
        for (int i = 0; i < MAX_PLANTS; i++) {
            plants.add(new Plant());
        }
        for (int i = 0; i < number_each_species; i++) {
            animals.add(new Wolf());
            animals.add(new Horse());
            animals.add(new Stag());
            animals.add(new Rabbit());
        }


    }

    public int getLengthAddress() {
        return lengthAddress;
    }

    public int getHeightAddress() {
        return heightAddress;
    }

    public void print() {
        System.out.println("Кол-во растений в клетке: " + lengthAddress + ":" + heightAddress + " равно " + plants.size());
        HashSet<Animal> wolves = (HashSet<Animal>) animals.stream().filter(s -> s instanceof Wolf).collect(Collectors.toSet());
        System.out.println("Кол-во волков в клетке: " + lengthAddress + ":" + heightAddress + " равно " + wolves.size());
        HashSet<Animal> horses = (HashSet<Animal>) animals.stream().filter(s -> s instanceof Horse ).collect(Collectors.toSet());
        System.out.println("Кол-во лошадей в клетке: " + lengthAddress + ":" + heightAddress + " равно " + horses.size());
        HashSet<Animal> stags = (HashSet<Animal>) animals.stream().filter(s -> s instanceof Stag ).collect(Collectors.toSet());
        System.out.println("Кол-во оленей в клетке: " + lengthAddress + ":" + heightAddress + " равно " + stags.size());
        HashSet<Animal> rabbits = (HashSet<Animal>) animals.stream().filter(s -> s instanceof Rabbit ).collect(Collectors.toSet());
        System.out.println("Кол-во кроликов в клетке: " + lengthAddress + ":" + heightAddress + " равно " + rabbits.size());
    }
}
