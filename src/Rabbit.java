public class Rabbit extends Herbivores {
    static {
        setMaxOffspringSize(20);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
    }

    public Rabbit() {
        setWeight(2);
        setSatiety(0);
    }
}
