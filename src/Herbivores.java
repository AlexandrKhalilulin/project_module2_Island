public abstract class Herbivores extends Animal {
    @Override
    public void eat(Cell cell) {
        for (int i = 0; i < getNumberAttemptsToEat(); i++) {
            Plant plant = cell.getPlants().stream().findAny().get();
            cell.getPlants().remove(plant);
            this.setSatiety(getSatiety() + plant.getWeight());
            if (getSatiety() >= getSatietyLimit()) {
                setSatiety(getSatietyLimit());
                break;
            }
        }
    }
}
