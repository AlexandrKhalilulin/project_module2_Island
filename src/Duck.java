public class Duck extends Omnivores{
    static {
        setMaxOffspringSize(10);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(0.15);
        setTravelSpeed(4);
        setWeight(4);
        setMapPreys(definePreyMap(Duck.class.getName()));
    }

    public Duck() {
        setSatiety(0);
    }
}
