public class Bear extends Carnivores {
    static {
        setMaxOffspringSize(1);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(80);
        setTravelSpeed(2);
        setMapPreys(definePreyMap(Bear.class.getName()));
    }

    public Bear() {
        setSatiety(0);
        setWeight(500);
    }
}
