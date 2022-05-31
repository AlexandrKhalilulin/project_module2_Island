public abstract class Entity {
    static double weight;

    public double getWeight() {
        return weight;
    }

    public static void setWeight(double weight) {
        Entity.weight = weight;
    }
}
