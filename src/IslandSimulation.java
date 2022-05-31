import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IslandSimulation {
    private static int durationSimulationCycle;
    private final int INITIAL_DELAY_START = 0;
    private final int PRINT_STEP = 2000;

    static {
         durationSimulationCycle = Integer.parseInt(new SettingsReader().getValue("islandsimulation", "Duration_Simulation_Cycle"));
    }

    public void start() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new LifeCycle(), INITIAL_DELAY_START, durationSimulationCycle, TimeUnit.MILLISECONDS);
        //scheduledExecutorService.scheduleAtFixedRate(new Print(), PRINT_STEP, PRINT_STEP, TimeUnit.MILLISECONDS);
    }


    private class LifeCycle implements Runnable {
        private int step;

        @Override
        public void run() {
            System.out.println("Cycle: " + step + ". Current island size: " + Island.SET_CELLS.size() + " cells");
            ExecutorService executorService = Executors.newCachedThreadPool();
            for (Cell cell : Island.SET_CELLS
            ) {
                executorService.submit(cell);

            }
            executorService.shutdown();
            Island.print();
            step++;
        }


    }

    private class Print implements Runnable {


        @Override
        public void run() {

            Island.print();

        }
    }


}
