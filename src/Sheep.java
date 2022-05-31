public class Sheep extends Herbivores{
    static {
        setMaxOffspringSize(3);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(15);
        setTravelSpeed(3);
        setWeight(70);
    }

    public Sheep() {
        setSatiety(0);
    }
}
