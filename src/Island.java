import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

public class Island implements Runnable{
    private static Island INSTANCE;
    private static Cell[][] map;
    private static int island_length;
    private static int island_height;

    private Island() {
        loadingSettingsFromProperties();
        map = new Cell[island_length][island_height];
        for (int i = 0; i < island_length; i++) {
            for (int j = 0; j < island_height; j++) {
                map[i][j] = new Cell(i, j);
            }
        }
    }

    public static synchronized Island getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Island();
        }
        return INSTANCE;
    }

    public static void print() {
        Set<Animal> allAnimals = new CopyOnWriteArraySet<>();
        Set<Plant> allPlants = new CopyOnWriteArraySet<>();
        for (Cell cell : getSetCells()
        ) {
            allPlants.addAll(cell.getPlants());
            allAnimals.addAll(cell.getAnimals());
        }
        Set<Animal> wolfs = allAnimals.stream().filter(s -> s instanceof Wolf).collect(Collectors.toSet());
        Set<Animal> horses = allAnimals.stream().filter(s -> s instanceof Horse).collect(Collectors.toSet());
        Set<Animal> stags = allAnimals.stream().filter(s -> s instanceof Stag).collect(Collectors.toSet());
        Set<Animal> rabbits = allAnimals.stream().filter(s -> s instanceof Rabbit).collect(Collectors.toSet());
        Set<Animal> boas = allAnimals.stream().filter(s -> s instanceof Boa).collect(Collectors.toSet());
        Set<Animal> foxes = allAnimals.stream().filter(s -> s instanceof Fox).collect(Collectors.toSet());
        Set<Animal> caterpillars = allAnimals.stream().filter(s -> s instanceof Caterpillar).collect(Collectors.toSet());
        Set<Animal> eagle = allAnimals.stream().filter(s -> s instanceof Eagle).collect(Collectors.toSet());
        Set<Animal> mouse = allAnimals.stream().filter(s -> s instanceof Mouse).collect(Collectors.toSet());

        System.out.println("Plant: " + allPlants.size() + " --- " + "Wolf: " + wolfs.size() + " --- " + "Fox: "
                    + foxes.size() + " --- " + "Boa: " + boas.size() + " --- " + "Eagle: " + eagle.size() + " --- "
                + "Horse: " + horses.size() + " --- " + "Stag: " + stags.size() + " --- " + "Rabbit: "
                + rabbits.size() + " --- " + "Mouse: " + mouse.size() + " --- " + "Caterpillar: " + caterpillars.size());


        int countAllEntity = allPlants.size() + allAnimals.size();

        double wolfPercent = (double) wolfs.size() / countAllEntity * 100;
        for (int i = 0; i < wolfPercent; i++) {
            System.out.print("\uD83D\uDC3A"); // 🐺
        }

        double boaPercent = (double) boas.size() / countAllEntity * 100;
        for (int i = 0; i < boaPercent; i++) {
            System.out.print("\uD83D\uDC0D"); //
        }

        double foxPercent = (double) foxes.size() / countAllEntity * 100;
        for (int i = 0; i < foxPercent; i++) {
            System.out.print("\uD83E\uDD8A"); //
        }

        double eaglePercent = (double) eagle.size() / countAllEntity * 100;
        for (int i = 0; i < eaglePercent; i++) {
            System.out.print("\uD83E\uDD85"); //
        }

        double stagPercent = (double) stags.size() / countAllEntity * 100;
        for (int i = 0; i < stagPercent; i++) {
            System.out.print("\uD83E\uDD8C");
        }
        double horsePercent = (double) horses.size() / countAllEntity * 100;
        for (int i = 0; i < horsePercent; i++) {
            System.out.print("\uD83D\uDC0E");
        }
        double rabbitPercent = (double) rabbits.size() / countAllEntity * 100;
        for (int i = 0; i < rabbitPercent; i++) {
            System.out.print("\uD83D\uDC07");
        }
        double mousePercent = (double) mouse.size() / countAllEntity * 100;
        for (int i = 0; i < mousePercent; i++) {
            System.out.print("\uD83D\uDC01");
        }

        double caterpillarPercent = (double)  caterpillars.size() / countAllEntity * 100;
        for (int i = 0; i < caterpillarPercent; i++) {
            System.out.print("\uD83D\uDC1B");
        }

        double plantPercent = (double) allPlants.size() / countAllEntity * 100;
        for (int i = 0; i < plantPercent; i++) {
            System.out.print("\uD83C\uDF3F");
        }
        System.out.println();

    }

    public static CopyOnWriteArraySet<Cell> getSetCells(){
        Set<Cell> cells = new CopyOnWriteArraySet<>();
        for (int i = 0; i < island_length; i++) {
            for (int j = 0; j < island_height; j++) {
                cells.add(map[i][j]);
            }
        }
        return (CopyOnWriteArraySet<Cell>) cells;
    }

    @Override
    public void run() {

    }

    public static void filling() {
        for (Cell cell : getSetCells()
        ) {
            cell.fillingCell();
        }
    }

    private void loadingSettingsFromProperties() {
        SettingsIsland settingsIsland = new SettingsIsland();
        island_length = Integer.parseInt(settingsIsland.getValue("Island_Length"));
        island_height = Integer.parseInt(settingsIsland.getValue("Island_Height"));
    }

    public Cell getCell(int newHeightAddress, int newHeightAddress1) {
        return null;
    }

    private class SettingsIsland {
        private final Properties properties = new Properties();

        public String getValue(String key) {
            InputStream inputStream = this.getClass().getResourceAsStream("island.properties");
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return this.properties.getProperty(key);
        }
    }

}
