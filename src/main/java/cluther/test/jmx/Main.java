package cluther.test.jmx;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;


public class Main {
    public static void main(String[] args) throws Exception {
        JMXTestObject testObject = new JMXTestObject();
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("jmx-tester:type=JMXTestObjectMBean");
        mbs.registerMBean(testObject, name);
        System.out.println("MBean registered: " + name.getCanonicalName());
        imitateActivity(testObject);
    }

    private static void imitateActivity(JMXTestObject testObject) {
        while (true) {
            try {
                testObject.update();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // Nothing
            }
        }
    }
}