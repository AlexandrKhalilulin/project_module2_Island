import java.util.*;
import java.util.stream.Collectors;

public class Cell implements Runnable {
    private Set<Animal> animals = new HashSet<>();
    private Set<Plant> plants = new HashSet<>();
    private final int MAX_PLANTS = 200;
    private final int lengthAddress;
    private final int heightAddress;
    private static int number_each_species = 1;
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
        sendingAnimalsToEat();
        reproduceAnimalsInCell();
    }

    private void reproduceAnimalsInCell() {
        Set<Animal> offspringSet = new HashSet<>();
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()){
            offspringSet.addAll((Collection<? extends Animal>) iterator.next().reproduce());
        }


        // Set<Animal> offspringSet = animals.stream().peek(Animal::reproduce).collect(Collectors.toSet());

        animals.addAll(offspringSet);
    }

    private void sendingAnimalsToEat() {
        animals.forEach(animal -> animal.eat(this));
    }

    private void resetSatiety() {
        animals = animals.stream().peek(s -> s.setSatiety(s.getSatiety() - s.getSatiety() / 100 * SATIETY_REDUCTION_PERCENTAGE)).collect(Collectors.toSet());
    }

    private void reproducePlantsInCell() {
        HashSet<Plant> newer = plants.stream().map(Plant::reproduce).flatMap(Collection::stream).collect(Collectors.toCollection(HashSet::new));
        Iterator<Plant> iterator = newer.iterator();
        while (iterator.hasNext() && plants.size() < MAX_PLANTS){
            plants.add(iterator.next());
        }
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
    }
}
