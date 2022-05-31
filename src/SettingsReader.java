import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SettingsReader {
    private static final Properties properties = new Properties();

    public String getValue(String name, String key) {
        InputStream inputStream = this.getClass().getResourceAsStream(name.toLowerCase(Locale.ROOT) + ".properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }

    public HashMap<Class, Integer> getMapClassInteger(String prefix) {
        InputStream inputStream = this.getClass().getResourceAsStream(prefix.toLowerCase(Locale.ROOT) + "Hunt" + ".properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<String> strings = properties.stringPropertyNames();
        HashMap<Class, Integer> lootMap = new HashMap<>();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String currentKey = iterator.next();
            try {
                lootMap.put(Class.forName(currentKey), Integer.valueOf(properties.getProperty(currentKey)));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return lootMap;
    }

}
