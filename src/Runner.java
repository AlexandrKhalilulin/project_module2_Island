public class Runner {
    public static void main(String[] args) {
        Island.getInstance();
        Island.filling();
        new IslandSimulation().start();
    }
}
