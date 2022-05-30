public class Stag extends Herbivores {
    static {
        setMaxOffspringSize(5);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
    }

    public Stag() {
        setSatiety(0);
        setWeight(300);
    }

}
