public class Buffalo extends Herbivores{
    static {
        setMaxOffspringSize(2);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(100);
        setTravelSpeed(3);
        setWeight(700);
    }

    public Buffalo() {
        setSatiety(0);
    }
}
