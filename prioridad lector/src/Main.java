import models.Monitor;
import threads.Escritor;
import threads.Lector;

public class Main {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        for (int i = 0; i < 3; i++) {
            new Thread(new Lector(monitor), "Lector-" + (i + 1)).start();
        }


        for (int i = 0; i < 3; i++) {
            new Thread(new Escritor(monitor), "Escritor-" + (i + 1)).start();
        }
    }
}
