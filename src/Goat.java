public class Goat extends Herbivores{
    static {
        setMaxOffspringSize(3);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(10);
        setTravelSpeed(3);
        setWeight(60);
    }

    public Goat() {
        setSatiety(0);
    }
}
