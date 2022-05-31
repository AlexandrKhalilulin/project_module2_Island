import java.util.concurrent.CopyOnWriteArraySet;

public class Plant extends Entity implements Grow, Reproduce {
    private final double max_weight = 5;
    private final int growFactor = 2;
    private final int birthFactor = 2;

    public Plant() {
        weight = max_weight / birthFactor;
    }

    @Override
    public void grow() {
        weight = getWeight() * growFactor;
        if (weight > max_weight) weight = max_weight;
    }

    @Override
    public CopyOnWriteArraySet<Plant> reproduce() {
        return makePlants((int) getWeight());
    }

    private CopyOnWriteArraySet<Plant> makePlants(int value) {
        CopyOnWriteArraySet<Plant> plants = new CopyOnWriteArraySet<>();
        for (int i = 0; i < value; i++) {
            plants.add(new Plant());
        }
        return plants;
    }


}
