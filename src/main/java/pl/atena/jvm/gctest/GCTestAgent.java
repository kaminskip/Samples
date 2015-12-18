package pl.atena.jvm.gctest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by paweka1 on 2015-12-18.
 */
public class GCTestAgent implements GCTestAgentMBean, Runnable {

    private List<Object> leakingList = new ArrayList<>();

    volatile double val = 10;

    private Object createObject(int size) {
        MyLeakingObject myLeakingObject = new MyLeakingObject();
        for (int i = 0; i < size; i++) {
            myLeakingObject.add(new Date().toString() + " " + i);
        }
        return myLeakingObject;
    }

    @Override
    public void newThread(String threadName) {
        Thread thread = new Thread(this);
        thread.setName(threadName);
        thread.start();
    }

    @Override
    public void newCollectableObject(int size) {
        createObject(size);
    }

    @Override
    public void newLeakedObject(int size) {
        leakingList.add(createObject(size));
    }

    @Override
    public void clearLeaked() {
        leakingList.clear();
    }

    @Override
    public void cpuIntensiveOperation(int iterations) {
        int[] myArrayToBeSorted = new int[] {4,3,6,7,2,8,1};
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < myArrayToBeSorted.length - 1; j++) {
                myArrayToBeSorted[j] = myArrayToBeSorted[j] + myArrayToBeSorted[j + 1];
            }
        }
    }

    @Override
    public void run() {
        for (;;) {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
