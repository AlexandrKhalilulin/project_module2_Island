import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Omnivores extends Carnivores{
    @Override
    public void eat(Cell cell) {
        int rand = ThreadLocalRandom.current().nextInt(0, 2);
        switch (rand){
            case 0: eatingAnimal(cell);

            case 1: eatingPlant(cell);
        }
    }

    private void eatingAnimal(Cell cell) {
        List<Animal> animals = new CopyOnWriteArrayList<>(cell.getAnimals());
        Collections.shuffle(animals);
        Animal prey;
        for (int i = 0; i < getNumberAttemptsToEat(); i++) {
            prey = animals.stream().filter(s -> mapPreys.containsKey(s.getClass())).findAny().get();
            if (this.hunting(prey)) {
                cell.getAnimals().remove(prey);
                animals.remove(prey);
                Collections.shuffle(animals);
                this.setSatiety(getSatiety() + prey.getWeight());
                if (getSatiety() > getSatietyLimit()) {
                    setSatiety(getSatietyLimit());
                    break;
                }
            }
        }
    }

    private void eatingPlant(Cell cell) {
        for (int i = 0; i < getNumberAttemptsToEat(); i++) {
            Plant plant = cell.getPlants().stream().findAny().get();
            cell.getPlants().remove(plant);
            this.setSatiety(getSatiety() + plant.getWeight());
            if (getSatiety() >= getSatietyLimit()) {
                setSatiety(getSatietyLimit());
                break;
            }
        }
    }

}
