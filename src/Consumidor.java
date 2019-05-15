

public class Consumidor implements Runnable {

    private final Buffer buffer;

    public Consumidor(Buffer buffer) {this.buffer = buffer;}

    @Override
    public void run() {

        int cant=3;
        while(cant!=0){

            Object o = buffer.read();
            System.out.println("Leido" + o.toString());
            cant--;
        }
    }
}
