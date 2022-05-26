import java.util.*;
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
        sendСarnivorousToEat(cell);
    }

    private void sendСarnivorousToEat(Cell cell) {
        List<Animal> animals = new ArrayList<>(cell.getAnimals());
        Collections.shuffle(animals);
        Animal prey;
        for (int i = 0; i < getNumberAttemptsToEat(); i++) {
            prey = animals.stream().filter(s -> mapPreys.keySet().contains(s.getClass())).findAny().get();
            if (this.hunting(prey)) {
                cell.getAnimals().remove(prey);
                this.setSatiety(getSatiety() + prey.getWeight());
                if (getSatiety() > getSatietyLimit()) setSatiety(getSatietyLimit());
                //System.out.println("Охота прошла успешно");
            }
        }
    }


    private boolean hunting(Animal prey) {
        int random = ThreadLocalRandom.current().nextInt(0, 100);
        Integer huntingSuccessRate = mapPreys.get(prey.getClass());
        if (random <= huntingSuccessRate) return true;
        return false;
    }

    public static HashMap<Class, Integer> definePreyMap(String prefix) {
        HashMap<Class, Integer> hashMap = new HashMap<>();
        SettingsReader settingsReader = new SettingsReader();
        hashMap = settingsReader.getPreyMap(prefix);
        return hashMap;
    }


}
