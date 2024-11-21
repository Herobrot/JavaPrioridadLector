package threads;

import models.Monitor;

public class Lector implements Runnable{
    private Monitor monitor;
    public Lector(Monitor monitor){
        this.monitor = monitor;
    }
    @Override
    public void run() {
        while (true){
            monitor.extraer();
        }
    }
}