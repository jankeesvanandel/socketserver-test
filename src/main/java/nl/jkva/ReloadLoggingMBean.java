package nl.jkva;

public interface ReloadLoggingMBean {
    void load(String configFilename);

    void reset();
}
