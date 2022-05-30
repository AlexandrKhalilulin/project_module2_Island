public class Wolf extends Carnivores {
    static {
        setMaxOffspringSize(1);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
        setMapPreys(definePreyMap(Wolf.class.getName()));
    }

    public Wolf() {
        setSatiety(0);
        setWeight(50);
    }

}
