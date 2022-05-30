import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Carnivores extends Animal {
    public static HashMap<Class, Integer> mapPreys;

    public static HashMap<Class, Integer> getMapPreys() {
        return mapPreys;
    }

    public static void setMapPreys(HashMap<Class, Integer> mapPreys) {
        Carnivores.mapPreys = mapPreys;
    }

    @Override
    public void eat(Cell cell) {

        List<Animal> animals = new CopyOnWriteArrayList<>(cell.getAnimals());
        Collections.shuffle(animals);
        Animal prey;
        for (int i = 0; i < getNumberAttemptsToEat(); i++) {
            prey = animals.stream().filter(s -> mapPreys.keySet().contains(s.getClass())).findAny().get();
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

    private boolean hunting(Animal prey) {
        int random = ThreadLocalRandom.current().nextInt(0, 100);
        Integer huntingSuccessRate = mapPreys.get(prey.getClass());
        return random <= huntingSuccessRate;
    }

    public static HashMap<Class, Integer> definePreyMap(String prefix) {
        HashMap<Class, Integer> hashMap;
        SettingsReader settingsReader = new SettingsReader();
        hashMap = settingsReader.getMapClassInteger(prefix);
        return hashMap;
    }

}
