import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
        for (Cell cell: getSetCells()
             ) {
            cell.print();
        }
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
