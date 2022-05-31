public class Boar extends Omnivores{
    static {
        setMaxOffspringSize(3);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(50);
        setTravelSpeed(2);
        setWeight(400);
        setMapPreys(definePreyMap(Boar.class.getName()));
    }

    public Boar() {
        setSatiety(0);
    }
}
