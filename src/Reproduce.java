import java.util.concurrent.CopyOnWriteArraySet;

public interface Reproduce {
    CopyOnWriteArraySet<? extends Entity> reproduce();
}
