import java.io.IOException;
import java.io.InputStream;
import java.util.*;
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
        Set<Animal> allAnimals = new HashSet<>();
        Set<Plant> allPlants = new HashSet<>();
        for (Cell cell : getSetCells()
        ) {
            allPlants.addAll(cell.getPlants());
            allAnimals.addAll(cell.getAnimals());
        }
        Set<Animal> wolfs = allAnimals.stream().filter(s -> s instanceof Wolf).collect(Collectors.toSet());
        Set<Animal> horses = allAnimals.stream().filter(s -> s instanceof Horse).collect(Collectors.toSet());
        Set<Animal> stags = allAnimals.stream().filter(s -> s instanceof Stag).collect(Collectors.toSet());
        Set<Animal> rabbits = allAnimals.stream().filter(s -> s instanceof Rabbit).collect(Collectors.toSet());

        System.out.println("Plants: " + allPlants.size() + " --- " + "Wolfs: " + wolfs.size() + " --- " + "Horses: " + horses.size() + " --- " + "Stags: " + stags.size() + " --- " + "Rabbits: " + rabbits.size());


        int countAllEntity = allPlants.size() + allAnimals.size();
        double wolfPercent = (double) wolfs.size() / countAllEntity * 100;
        for (int i = 0; i < wolfPercent; i++) {
            System.out.print("\uD83D\uDC3A"); // 🐺
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
        double plantPercent = (double) allPlants.size() / countAllEntity * 100;
        for (int i = 0; i < plantPercent; i++) {
            System.out.print("\uD83C\uDF3F");
        }
        System.out.println();

    }

    public Cell[][] getMap() {
        return map;
    }

    public void setMap(Cell[][] map) {
        Island.map = map;
    }

    public static HashSet<Cell> getSetCells(){
        HashSet<Cell> cellHashSet = new HashSet<>();
        for (int i = 0; i < island_length; i++) {
            for (int j = 0; j < island_height; j++) {
                cellHashSet.add(map[i][j]);
            }
        }
        return cellHashSet;
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
