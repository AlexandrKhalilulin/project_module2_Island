import java.util.HashMap;
import java.util.Iterator;
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
        //System.out.println("Animal " + this + " going to hunt, satiety is: " + getSatiety());
        Animal prey = null;
        Boolean flag = false;
        while (!flag) {
            int rand = ThreadLocalRandom.current().nextInt(0, cell.getAnimals().size());
            Iterator<Animal> iterator = cell.getAnimals().iterator();
            for (int i = 0; i < rand; i++) {
                prey =  iterator.next();
            }
            Class clazz = prey.getClass();
            if (mapPreys.keySet().contains(clazz)) flag = true;
        }
        //System.out.println(this + " find a prey: " + prey);
        if (this.hunting(prey)) {
            cell.getAnimals().remove(prey);
            this.setSatiety(getSatiety() + prey.getWeight());
            if (getSatiety() > getSatietyLimit()) setSatiety(getSatietyLimit());
            //System.out.println("Охота прошла успешно");
        }
    }


    private boolean hunting(Animal prey) {
        int rand = ThreadLocalRandom.current().nextInt(0, 100);
        Integer integer = mapPreys.get(prey.getClass());
        if (rand <= integer) return true;
        return false;
    }

    public static HashMap<Class, Integer> definePreyMap(String prefix) {
        HashMap<Class, Integer> hashMap = new HashMap<>();
        SettingsReader settingsReader = new SettingsReader();
        hashMap = settingsReader.getPreyMap(prefix);
        return hashMap;
    }


}
