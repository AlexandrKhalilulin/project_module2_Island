public class Horse extends Herbivores {
    static {
        setMaxOffspringSize(5);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(60);
        setTravelSpeed(1);
        setWeight(400);
    }

    public Horse() {
        setSatiety(0);
    }

}
