import models.*;
import threads.*;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        EntradaLector entradaLector = new EntradaLector(monitor);
        SalidaLector salidaLector = new SalidaLector(monitor);
        EntradaEscritor entradaEscritor = new EntradaEscritor(monitor);
        SalidaEscritor salidaEscritor = new SalidaEscritor(monitor);

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                while (true) {
                    entradaLector.entrar();
                    monitor.extraer();
                    salidaLector.salir();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }, "Lector-" + (i + 1)).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                while (true) {
                    entradaEscritor.entrar();
                    monitor.insertar();
                    salidaEscritor.salir();

                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        throw new RuntimeException(e);
                    }
                }
            }, "Escritor-" + (i + 1)).start();
        }
    }
}
