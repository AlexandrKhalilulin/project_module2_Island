public class Horse extends Herbivores {
    static {
        setMaxOffspringSize(5);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
    }


    public Horse() {
        setWeight(400);
        setSatiety(0);
    }

}
