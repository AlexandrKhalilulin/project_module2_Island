public class Boar extends Herbivores{
    static {
        setMaxOffspringSize(3);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(50);
        setTravelSpeed(2);
        setWeight(400);
    }

    public Boar() {
        setSatiety(0);
    }
}
