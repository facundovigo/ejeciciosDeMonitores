import java.util.concurrent.locks.Condition;

public class Buffer {

    Condition hayEspacio;
    private Object[] data;
    private int inicio=0;
    private int fin=0;

    public Buffer(int n){this.data = new Object[n];}

    public synchronized void write(Object o){

        while((inicio+1 % this.data.length)==fin){

            try {
                this.hayEspacio.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.data[inicio]=o;
        this.inicio=inicio +1 % this.data.length;
        notifyAll();
    }

    public synchronized Object read(){
        while(inicio==fin){
            try {
                hayEspacio.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Object aux = data[fin];
        data[fin]=null;
        fin=fin +1 % data.length;
        notifyAll();
        return aux;
    }

    public static void main(String[]args) {

        Buffer buffer = new Buffer(3);
        Productor productor = new Productor(buffer);
        Consumidor consumidor = new Consumidor(buffer);
        Thread ct = new Thread(consumidor);
        //buffer.write(3);

        productor.start();
        ct.start();

        /*
        productor.run();
        for (int i = 0; i <= 2 ; i++) {

            System.out.println("Leido" + buffer.read());
        }*/

    }

}
