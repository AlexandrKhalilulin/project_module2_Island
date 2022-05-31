public class Mouse extends Omnivores {
    static {
        setMaxOffspringSize(20);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(0.001);
        setTravelSpeed(1);
        setWeight(0.05);
        setMapPreys(definePreyMap(Mouse.class.getName()));
    }

    public Mouse() {
        setSatiety(0);
    }
}
