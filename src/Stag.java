public class Stag extends Herbivores {
    static {
        setClassProperties(Stag.class.getName());
    }

    public Stag() {
        setSatiety(0);
        setWeight(300);
    }

    @Override
    public void move() {
    }

}
