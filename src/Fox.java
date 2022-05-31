public class Fox extends Carnivores {
    static {
        setMaxOffspringSize(2);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
        setWeight(8);
        setMapPreys(definePreyMap(Fox.class.getName()));
    }

    public Fox() {
        setSatiety(0);
    }

}
