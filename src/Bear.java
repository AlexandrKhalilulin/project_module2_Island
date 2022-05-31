public class Bear extends Carnivores {
    static {
        setMaxOffspringSize(1);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(80);
        setTravelSpeed(2);
        setWeight(500);
        setMapPreys(definePreyMap(Bear.class.getName()));
    }

    public Bear() {
        setSatiety(0);
    }
}
