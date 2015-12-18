package pl.atena.jvm.gctest;

/**
 * Created by paweka1 on 2015-12-18.
 */
public interface GCTestAgentMBean {

    void newThread(String threadName);

    void newCollectableObject(int size);

    void newLeakedObject(int size);

    void clearLeaked();

    void cpuIntensiveOperation(int iterations);
}
