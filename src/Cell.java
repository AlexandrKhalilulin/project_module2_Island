import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class Cell implements Runnable {
    private static final int number_each_species = 4;
    private final int MAX_PLANTS = 200;
    private final int lengthAddress;
    private final int heightAddress;
    private final int SATIETY_REDUCTION_PERCENTAGE = 100;
    private Set<Animal> animals = new CopyOnWriteArraySet<>();
    private Set<Plant> plants = new CopyOnWriteArraySet<>();

    public Cell(int lengthAddress, int heightAddress) {
        this.lengthAddress = lengthAddress;
        this.heightAddress = heightAddress;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public Set<Plant> getPlants() {
        return plants;
    }

    @Override
    public void run() {
        resetSatiety();
        growingUpPlants();
        reproducePlantsInCell();
        animalsCycle();
        cellCleaner();
    }

    private void animalsCycle() {

        //eating
        for (Animal animal: animals
             ) {
            animal.eat(this);
        }

        //moving
        Map<Cell, CopyOnWriteArraySet<Animal>> movingAnimalsMap = new HashMap<>();
        for (Cell cell : Island.SET_CELLS
        ) {
            movingAnimalsMap.put(cell, new CopyOnWriteArraySet<>());
        }
        for (Animal animal : animals
        ) {
            Optional<Cell> move = animal.move(this);
            if (move.isPresent()) {
                movingAnimalsMap.get(move.get()).add(animal);
                this.animals.remove(animal);
            }
        }
        for (Cell cell : Island.SET_CELLS
        ) {
            CopyOnWriteArraySet<Animal> movingAnimals = movingAnimalsMap.get(cell);
            cell.animals.addAll(movingAnimals);
        }

        //reproducing
        Set<Animal> offspringSet = new HashSet<>();
        Iterator<Animal> iterator = animals.iterator();
        while (iterator.hasNext()){
            offspringSet.addAll((Set<Animal>) iterator.next().reproduce());
        }
        animals.addAll(offspringSet);

    }

    private void resetSatiety() {
        animals.stream().peek(s -> s.setSatiety(s.getSatiety() - s.getSatiety() / 100 * SATIETY_REDUCTION_PERCENTAGE));
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
            animals.add(new Boa());
            animals.add(new Fox());
            animals.add(new Caterpillar());
            animals.add(new Eagle());
            animals.add(new Mouse());
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
        HashSet<Animal> horses = (HashSet<Animal>) animals.stream().filter(s -> s instanceof Horse).collect(Collectors.toSet());
        System.out.println("Кол-во лошадей в клетке: " + lengthAddress + ":" + heightAddress + " равно " + horses.size());
        HashSet<Animal> stags = (HashSet<Animal>) animals.stream().filter(s -> s instanceof Stag).collect(Collectors.toSet());
        System.out.println("Кол-во оленей в клетке: " + lengthAddress + ":" + heightAddress + " равно " + stags.size());
        HashSet<Animal> rabbits = (HashSet<Animal>) animals.stream().filter(s -> s instanceof Rabbit).collect(Collectors.toSet());
        System.out.println("Кол-во кроликов в клетке: " + lengthAddress + ":" + heightAddress + " равно " + rabbits.size());
    }

    private void cellCleaner() {
        Set<Animal> wolfs = animals.stream().filter(s -> s instanceof Wolf).limit(30).collect(Collectors.toSet());
        Set<Animal> horses = animals.stream().filter(s -> s instanceof Horse).limit(40).collect(Collectors.toSet());
        Set<Animal> stags = animals.stream().filter(s -> s instanceof Stag).limit(20).collect(Collectors.toSet());
        Set<Animal> rabbits = animals.stream().filter(s -> s instanceof Rabbit).limit(150).collect(Collectors.toSet());
        Set<Animal> boas = animals.stream().filter(s -> s instanceof Boa).limit(30).collect(Collectors.toSet());
        Set<Animal> foxes = animals.stream().filter(s -> s instanceof Fox).limit(30).collect(Collectors.toSet());
        Set<Animal> caterpillar = animals.stream().filter(s -> s instanceof Caterpillar).limit(1000).collect(Collectors.toSet());
        Set<Animal> mouse = animals.stream().filter(s -> s instanceof Mouse).limit(500).collect(Collectors.toSet());
        Set<Animal> eagle = animals.stream().filter(s -> s instanceof Eagle).limit(20).collect(Collectors.toSet());


        Set<Animal> newSet = new HashSet<>();
        newSet.addAll(wolfs);
        newSet.addAll(horses);
        newSet.addAll(stags);
        newSet.addAll(rabbits);
        newSet.addAll(boas);
        newSet.addAll(foxes);
        newSet.addAll(caterpillar);
        newSet.addAll(mouse);
        newSet.addAll(eagle);

        animals = newSet;
    }

}
