package threads;

import models.Monitor;

public class SalidaLector {
    private final Monitor monitor;

    public SalidaLector(Monitor monitor) {
        this.monitor = monitor;
    }

    public synchronized void salir() {
        monitor.decrementarLectoresActivos();

        if (monitor.getLectoresActivos() == 0) {
            this.notifyAll();
        }
    }
}
