package threads;

import models.Monitor;

public class EntradaEscritor {
    private final Monitor monitor;

    public EntradaEscritor(Monitor monitor) {
        this.monitor = monitor;
    }

    public synchronized void entrar() {
        while (monitor.isEscritorActivo() || monitor.getLectoresActivos() > 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        monitor.setEscritorActivo(true);
    }
}
