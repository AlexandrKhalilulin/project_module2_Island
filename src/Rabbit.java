public class Rabbit extends Herbivores{
    static {
        setClassProperties(Rabbit.class.getName());
    }

    @Override
    public void move() {

    }


    public Rabbit() {
        setWeight(2);
        setSatiety(0);
    }
}
