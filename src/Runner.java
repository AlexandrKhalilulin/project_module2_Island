import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

public class Runner {
    static List<Consumer<String>> list = new ArrayList<>();


    public static void main(String[] args) {
        Consumer<String> srtingtoUppercase = str -> System.out.println(str.toLowerCase(Locale.ROOT));
                list.add(srtingtoUppercase);
        for (Consumer con: list
             ) {
            con.accept("Hello");
        }





        Island.getInstance();
        Island.filling();
        new IslandSimulation().start();
    }

    public static void m(String string){
        list.forEach(s -> s.accept(string));
    }

}
