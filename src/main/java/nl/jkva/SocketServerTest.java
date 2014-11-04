package nl.jkva;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class SocketServerTest {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(SocketServerTest.class);

    public static void main(String[] args) throws Exception {
        registerMBean();

        while (true) {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1);
                LOG.info("Test: " + i);
            }
        }
    }

    private static void registerMBean() throws Exception {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(new ReloadLogging(), new ObjectName("nl.jkva.SocketServerTest:type=ReloadLogging"));
    }
}
