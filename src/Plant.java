import java.util.HashSet;

public class Plant extends Entity implements Grow, Reproduce{
    private double max_weight = 5;
    private int growFactor = 2;
    private int birthFactor = 2;

    public Plant() {
        weight = max_weight / birthFactor;
    }

    @Override
    public void grow() {
        weight = getWeight() * growFactor;
        if (weight > max_weight) weight = max_weight;
    }

    @Override
    public HashSet<Plant> reproduce() {
        return makePlants((int) getWeight());
    }

    private HashSet<Plant> makePlants (int value){
        HashSet<Plant> plants = new HashSet<>();
        for (int i = 0; i < value; i++) {
            plants.add(new Plant());
        }
        return plants;
    }


}
