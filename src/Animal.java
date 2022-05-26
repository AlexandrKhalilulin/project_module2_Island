import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity implements Eating, Moving, Reproduce {
    private static int MaxOffspringSize;
    private static int MinOffspringSize;
    private static int LivestockSize;
    private static int TravelSpeed;
    private static double SatietyLimit;
    private static int NumberAttemptsToEat;
    private double satiety;

    public static int getMinOffspringSize() {
        return MinOffspringSize;
    }

    public static int getNumberAttemptsToEat() {
        return NumberAttemptsToEat;
    }

    public static void setNumberAttemptsToEat(int numberAttemptsToEat) {
        NumberAttemptsToEat = numberAttemptsToEat;
    }

    public static void setMinOffspringSize(int minOffspringSize) {
        MinOffspringSize = minOffspringSize;
    }

    public static int getMaxOffspringSize() {
        return MaxOffspringSize;
    }

    public static void setMaxOffspringSize(int maxOffspringSize) {
        MaxOffspringSize = maxOffspringSize;
    }

    public static int getLivestockSize() {
        return LivestockSize;
    }

    public static void setLivestockSize(int livestockSize) {
        LivestockSize = livestockSize;
    }

    public static int getTravelSpeed() {
        return TravelSpeed;
    }

    public static void setTravelSpeed(int travelSpeed) {
        TravelSpeed = travelSpeed;
    }

    public static double getSatietyLimit() {
        return SatietyLimit;
    }

    public static void setSatietyLimit(double satietyLimit) {
        SatietyLimit = satietyLimit;
    }

    @Override
    public HashSet<? extends Entity> reproduce() {
        HashSet<Animal> offspringSet = new HashSet<>();
        int rand = ThreadLocalRandom.current().nextInt(getMinOffspringSize(), getMaxOffspringSize() + 1);
        AnimalFactory animalFactory = new AnimalFactory();
        for (int i = 0; i < rand; i++) {
            offspringSet.add(animalFactory.createInstance(this.getClass()));
        }
        return offspringSet;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    public static void setClassProperties(String name) {
        SettingsReader settingsReader = new SettingsReader();
        setMaxOffspringSize(Integer.parseInt(settingsReader.getValue(name, "MaxOffspringSize")));
        setMinOffspringSize(Integer.parseInt(settingsReader.getValue(name, "MinOffspringSize")));
        setLivestockSize(Integer.parseInt(settingsReader.getValue(name, "LivestockSize")));
        setTravelSpeed(Integer.parseInt(settingsReader.getValue(name, "TravelSpeed")));
        setSatietyLimit(Integer.parseInt(settingsReader.getValue(name, "SatietyLimit")));
        setNumberAttemptsToEat(Integer.parseInt(settingsReader.getValue(name, "NumberAttemptsToEat")));
    }

}
