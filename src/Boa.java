public class Boa extends Carnivores {
    static {
        setMaxOffspringSize(2);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(5);
        setTravelSpeed(1);
        setWeight(15);
        setMapPreys(definePreyMap(Boa.class.getName()));
    }


    public Boa() {
        setSatiety(0);
    }

}
