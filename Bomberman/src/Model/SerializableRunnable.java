package Model;

import java.io.Serializable;

@FunctionalInterface
public interface SerializableRunnable extends Serializable, Runnable {
    
    @Override
    public abstract void run();
    
}
