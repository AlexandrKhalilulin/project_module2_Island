public class Horse extends Herbivores {
    static {
        setClassProperties(Horse.class.getName());
    }

    public Horse() {
        setWeight(400);
        setSatiety(0);
    }

    @Override
    public void move() {
    }

}
