public class Caterpillar extends Herbivores {
    static {
        setMaxOffspringSize(100);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
    }

    public Caterpillar() {
        setWeight(0.01);
        setSatiety(0);
    }

}
