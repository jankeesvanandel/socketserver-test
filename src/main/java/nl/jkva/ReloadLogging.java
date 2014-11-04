package nl.jkva;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.classic.net.server.ServerSocketAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

public class ReloadLogging implements ReloadLoggingMBean {
    @Override
    public void load(String configFileName) {
        // Stop logback
        ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
        LoggerContext loggerContext = (LoggerContext) iLoggerFactory;
            /*ch.qos.logback.classic.Logger rootLogger = loggerContext.getLogger("ROOT");
            Iterator<Appender<ILoggingEvent>> appenderIterator = rootLogger.iteratorForAppenders();
            while (appenderIterator.hasNext()) {
                Appender<ILoggingEvent> next = appenderIterator.next();
                if (next instanceof AbstractServerSocketAppender) {
                    AbstractServerSocketAppender next1 = (AbstractServerSocketAppender) next;
                    next1.stop();
                    Field runner1 = AbstractServerSocketAppender.class.getDeclaredField("runner");
                    runner1.setAccessible(true);
                    Object runner = runner1.get(next1);
                    Field clients1 = ConcurrentServerRunner.class.getDeclaredField("clients");
                    clients1.setAccessible(true);
                    Field listener1 = ConcurrentServerRunner.class.getDeclaredField("listener");
                    listener1.setAccessible(true);
                    List clients = (List) (clients1.get(runner));
                    if (!clients.isEmpty()) {
                        Object firstClient = clients.get(0);
                        Field socket1 = firstClient.getClass().getDeclaredField("socket");
                        socket1.setAccessible(true);
                        Socket socket = (Socket) (socket1.get(firstClient));
                        if (!socket.isClosed()) {
                            System.out.println("SOCKET stopped!!!");
                            socket.shutdownOutput();
                            socket.shutdownInput();
//                            socket.close();
                        }
                    }
                    Object listener = listener1.get(runner);
                    if (listener != null) {
                        listener.getClass().getMethod("close").invoke(listener);
                    }
                }
            }*/
        loggerContext.stop();

        // Start logback
        JoranConfigurator joranConfigurator = new JoranConfigurator();
        joranConfigurator.setContext(loggerContext);
        loggerContext.reset();
        try {
            joranConfigurator.doConfigure(configFileName);
        } catch (JoranException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void reset() {

    }

//    private static boolean findServerSocketAppender(LoggerContext loggerContext) {
//        ch.qos.logback.classic.Logger root = loggerContext.getLogger("ROOT");
//        Iterator<Appender<ILoggingEvent>> appenderIterator1 = root.iteratorForAppenders();
//        while (appenderIterator1.hasNext()) {
//            Appender<ILoggingEvent> next = appenderIterator1.next();
//            if (next instanceof ServerSocketAppender) {
//                ServerSocketAppender serverSocketAppender = (ServerSocketAppender) next;
//                return serverSocketAppender.isStarted();
//            }
//        }
//        return false;
//    }
}
