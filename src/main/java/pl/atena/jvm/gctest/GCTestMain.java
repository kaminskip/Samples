package pl.atena.jvm.gctest;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by paweka1 on 2015-12-18.
 */
public class GCTestMain {

    private static void init() throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        GCTestAgent agent = new GCTestAgent();
        ObjectName agentName = new ObjectName("PVTests:name=GCTestAgent");
        mBeanServer.registerMBean(agent, agentName);
    }

    public static void main(String... strings) throws Exception {
        init();
        for (;;) {
            Thread.sleep(1000);
        }
    }
}
