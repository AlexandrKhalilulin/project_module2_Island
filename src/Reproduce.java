import java.util.HashSet;
import java.util.Optional;

public interface Reproduce {
    HashSet<? extends Entity> reproduce();
}
