package models;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Monitor {
    private final int TOTAL = 10;
    private int[] buffer = new int[TOTAL];
    private int lleno;

    private int lectoresActivos = 0;
    private int lectoresEnEspera = 0;
    private boolean escritorActivo = false;

    private Random random = new Random(System.currentTimeMillis());

    public Monitor() {
        lleno = 0;
        Arrays.fill(buffer, 0);
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "buffer=" + Arrays.toString(buffer) +
                '}';
    }

    public synchronized void insertar() {
        int valor;

        valor = random.nextInt(400) + 100;

        while (lectoresActivos > 0 || escritorActivo || lleno == TOTAL) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        escritorActivo = true;

        for (int i = 0; i < TOTAL; i++) {
            if (buffer[i] == 0) {
                buffer[i] = valor;
                lleno++;
                break;
            }
        }

        System.out.println(Thread.currentThread().getName() + " escribió: " + this.toString());

        escritorActivo = false;
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.notify();
    }

    public synchronized void extraer() {
        int valor;

        lectoresEnEspera++;

        while (escritorActivo || lleno == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }

        lectoresEnEspera--;
        lectoresActivos++;

        for (int i = 0; i < TOTAL; i++) {
            if (buffer[i] != 0) {
                valor = buffer[i];
                buffer[i] = 0;
                lleno--;
                System.out.println(Thread.currentThread().getName() + " leyó: " + valor);
                break;
            }
        }

        lectoresActivos--;
        if (lectoresActivos == 0) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.notify();
        }
    }
}
