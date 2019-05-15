public class Productor extends Thread {

    private final Buffer buffer;


    Productor(Buffer buffer){this.buffer = buffer;}

    public void run(){
        int i = 0;
        int cant = 3;
        while(cant!=0){
            buffer.write(i);
            i++;
            cant--;
        }
    }
}
