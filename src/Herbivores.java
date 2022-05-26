public abstract class Herbivores extends Animal {

    @Override
    public void eat(Cell cell) {
        //System.out.println("Травоядное идет кушать" + this);
        for (int i = 0; i < getNumberAttemptsToEat(); i++) {
            Plant plant = cell.getPlants().stream().findAny().get();
            //System.out.println("Травоядным " + this + " найдено растение" + plant);
            cell.getPlants().remove(plant);
            this.setSatiety(getSatiety() + plant.getWeight());
            if (getSatiety() >= getSatietyLimit()) {
                setSatiety(getSatietyLimit());
                break;
            }
        }
        /*
        while (getSatiety() < getSatietyLimit()){
            Plant plant = cell.getPlants().stream().findAny().get();
            //System.out.println("Травоядным " + this + " найдено растение" + plant);
            cell.getPlants().remove(plant);

            this.setSatiety(getSatiety() + plant.getWeight());
            if (getSatiety() > getSatietyLimit()) setSatiety(getSatietyLimit());
            //System.out.println("Растение сьедено. Сытость травоядного равна: " + getSatiety());
        }*/
    }
}
