public class Fox extends Carnivores {
    static {
        setMaxOffspringSize(2);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
        setMapPreys(definePreyMap(Fox.class.getName()));
    }

    public Fox() {
        setWeight(8);
        setSatiety(0);
    }

}
