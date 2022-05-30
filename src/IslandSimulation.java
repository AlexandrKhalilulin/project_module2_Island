import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class IslandSimulation {
    private final int BAR_DURATION = 2000;
    private final int INITIAL_DELAY_START = 0;
    private final int PRINT_STEP = 2000;
    private int step;

    public void start() {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new LifeCycle(), INITIAL_DELAY_START, BAR_DURATION, TimeUnit.MILLISECONDS);
        // scheduledExecutorService.scheduleAtFixedRate(new Print(), PRINT_STEP, PRINT_STEP, TimeUnit.MILLISECONDS);
    }


    private class LifeCycle implements Runnable {
        @Override
        public void run() {
            System.out.println("Cycle: " + step);
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.submit(new Print());
            for (Cell cell : Island.getSetCells()
            ) {
                executorService.submit(cell);

            }
            executorService.shutdown();
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
