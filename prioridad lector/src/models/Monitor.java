package models;

import java.util.Arrays;
import java.util.Random;

public class Monitor {
    private final int TOTAL = 10;
    private int[] buffer = new int[TOTAL];
    private int lleno = 0;
    private int lectoresActivos = 0;
    private int lectoresEnEspera = 0;
    private boolean escritorActivo = false;

    private final Random random = new Random(System.currentTimeMillis());

    public Monitor() {
        Arrays.fill(buffer, 0);
    }

    public synchronized void insertar() {
        int valor = random.nextInt(400) + 100;

        for (int i = 0; i < TOTAL; i++) {
            if (buffer[i] == 0) {
                buffer[i] = valor;
                lleno++;
                break;
            }
        }

        System.out.println(Thread.currentThread().getName() + " escribió: " + this);
    }

    public synchronized void extraer() {
        for (int i = 0; i < TOTAL; i++) {
            if (buffer[i] != 0) {
                int valor = buffer[i];
                buffer[i] = 0;
                lleno--;
                System.out.println(Thread.currentThread().getName() + " leyó: " + valor);
                break;
            }
        }
    }

    public synchronized boolean isEscritorActivo() {
        return escritorActivo;
    }

    public synchronized void setEscritorActivo(boolean activo) {
        this.escritorActivo = activo;
    }

    public synchronized int getLectoresActivos() {
        return lectoresActivos;
    }

    public synchronized void incrementarLectoresActivos() {
        lectoresActivos++;
    }

    public synchronized void decrementarLectoresActivos() {
        lectoresActivos--;
    }

    public synchronized void incrementarLectoresEnEspera() {
        lectoresEnEspera++;
    }

    public synchronized void decrementarLectoresEnEspera() {
        lectoresEnEspera--;
    }

    @Override
    public String toString() {
        return "Monitor{" +
                "buffer=" + Arrays.toString(buffer) +
                ", lleno=" + lleno +
                ", lectoresActivos=" + lectoresActivos +
                ", lectoresEnEspera=" + lectoresEnEspera +
                ", escritorActivo=" + escritorActivo +
                '}';
    }
}
