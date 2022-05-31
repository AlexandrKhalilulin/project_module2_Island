public class Mouse extends Herbivores {
    static {
        setMaxOffspringSize(20);
        setMinOffspringSize(1);
        setNumberAttemptsToEat(1);
        setSatietyLimit(0.001);
        setTravelSpeed(1);
    }

    public Mouse() {
        setSatiety(0);
        setWeight(0.05);
    }
}
