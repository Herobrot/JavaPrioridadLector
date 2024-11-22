package threads;

import models.Monitor;

public class EntradaLector {
    private final Monitor monitor;

    public EntradaLector(Monitor monitor) {
        this.monitor = monitor;
    }

    public synchronized void entrar() {
        monitor.incrementarLectoresEnEspera();

        while (monitor.isEscritorActivo()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        monitor.decrementarLectoresEnEspera();
        monitor.incrementarLectoresActivos();
    }
}
