package threads;

import models.Monitor;

public class Escritor implements Runnable{
    private Monitor monitor;
    public Escritor(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        while (true){
            monitor.insertar();
        }
    }
}