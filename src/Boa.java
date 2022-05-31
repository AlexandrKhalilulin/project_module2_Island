public class Boa extends Carnivores {
    static {
        setMaxOffspringSize(2);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
        setMapPreys(definePreyMap(Boa.class.getName()));
    }


    public Boa() {
        setWeight(15);
        setSatiety(0);
    }

}
