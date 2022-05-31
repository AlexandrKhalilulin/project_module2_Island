import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity implements Eating, Moving, Reproduce {
    private static final int MOVE_PERCENT = 20;
    private static int MaxOffspringSize;
    private static int MinOffspringSize;
    private static int TravelSpeed;
    private static double SatietyLimit;
    private static int NumberAttemptsToEat;
    private double satiety;

    public static int getMinOffspringSize() {
        return MinOffspringSize;
    }

    public static void setMinOffspringSize(int minOffspringSize) {
        MinOffspringSize = minOffspringSize;
    }

    public static int getNumberAttemptsToEat() {
        return NumberAttemptsToEat;
    }

    public static void setNumberAttemptsToEat(int numberAttemptsToEat) {
        NumberAttemptsToEat = numberAttemptsToEat;
    }

    public static int getMaxOffspringSize() {
        return MaxOffspringSize;
    }

    public static void setMaxOffspringSize(int maxOffspringSize) {
        MaxOffspringSize = maxOffspringSize;
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
    public Optional<Cell> move(Cell cell) {
        Optional<Cell> cellOptional = Optional.empty();
        int random = ThreadLocalRandom.current().nextInt(0, 100);
        if (random <= MOVE_PERCENT) {
            Set<Directions> setDirections = getSetDirections(getTravelSpeed());
            int newLengthAddress = cell.getLengthAddress();
            int newHeightAddress = cell.getHeightAddress();
            for (Directions s : setDirections
            ) {
                switch (s) {
                    case Left:
                        newLengthAddress--;
                    case Right:
                        newLengthAddress++;
                    case Up:
                        newHeightAddress++;
                    case Down:
                        newLengthAddress--;
                }
                if (newLengthAddress == Island.island_length) {
                    newLengthAddress = 0;
                }
                if (newLengthAddress < 0) {
                    newLengthAddress = Island.island_length -1;
                }
                if (newHeightAddress == Island.island_height) {
                    newHeightAddress = 0;
                }
                if (newHeightAddress < 0) {
                    newHeightAddress = Island.island_height - 1;
                }
            }
            cellOptional = Island.getCell(newLengthAddress, newHeightAddress);

        }
        return cellOptional;
    }

    private Set<Directions> getSetDirections(int travelSpeed) {
        Set<Directions> directions = new HashSet<>();
        for (int i = 0; i < travelSpeed; i++) {
            int random = ThreadLocalRandom.current().nextInt(0, 4);
            if (random == 0) directions.add(Directions.Left);
            if (random == 1) directions.add(Directions.Right);
            if (random == 2) directions.add(Directions.Up);
            if (random == 3) directions.add(Directions.Down);
        }
        return directions;
    }

    @Override
    public CopyOnWriteArraySet<? extends Entity> reproduce() {
        Set<Animal> offspringSet = new CopyOnWriteArraySet<>();
        int rand = ThreadLocalRandom.current().nextInt(getMinOffspringSize(), getMaxOffspringSize());
        AnimalFactory animalFactory = new AnimalFactory();
        for (int i = 0; i < rand; i++) {
            offspringSet.add(animalFactory.createInstance(this.getClass()));
        }
        return (CopyOnWriteArraySet<? extends Entity>) offspringSet;
    }

    public double getSatiety() {
        return satiety;
    }

    public void setSatiety(double satiety) {
        this.satiety = satiety;
    }

    public void setClassProperties(String name) {
        SettingsReader settingsReader = new SettingsReader();
        setMaxOffspringSize(Integer.parseInt(settingsReader.getValue(name, "MaxOffspringSize")));
        setMinOffspringSize(Integer.parseInt(settingsReader.getValue(name, "MinOffspringSize")));

        setTravelSpeed(Integer.parseInt(settingsReader.getValue(name, "TravelSpeed")));
        setSatietyLimit(Double.parseDouble(settingsReader.getValue(name, "SatietyLimit")));
        setNumberAttemptsToEat(Integer.parseInt(settingsReader.getValue(name, "NumberAttemptsToEat")));
    }

}
