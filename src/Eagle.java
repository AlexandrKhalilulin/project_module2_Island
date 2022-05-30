public class Eagle extends Carnivores{
    static {
        setMaxOffspringSize(2);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(1);
        setTravelSpeed(3);
        setMapPreys(definePreyMap(Eagle.class.getName()));
    }

    public Eagle() {
        setSatiety(0);
        setWeight(6);
    }
}
