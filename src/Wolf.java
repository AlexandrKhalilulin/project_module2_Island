import java.util.HashMap;

public class Wolf extends Carnivores {
    static {
        setMapPreys(definePreyMap(Wolf.class.getSimpleName()));
        setClassProperties(Wolf.class.getSimpleName());
    }

    public Wolf() {
        setSatiety(0);
        setWeight(50);
    }

    @Override
    public void move() {
    }

}
