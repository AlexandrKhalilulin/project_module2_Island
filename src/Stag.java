public class Stag extends Herbivores {
    static {
        setMaxOffspringSize(5);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(50);
        setTravelSpeed(1);
        setWeight(300);
    }

    public Stag() {
        setSatiety(0);
    }

}
