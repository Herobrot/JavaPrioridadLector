package threads;

import models.Monitor;

public class SalidaEscritor {
    private final Monitor monitor;

    public SalidaEscritor(Monitor monitor) {
        this.monitor = monitor;
    }

    public synchronized void salir() {
        monitor.setEscritorActivo(false);
        this.notifyAll();
    }
}
