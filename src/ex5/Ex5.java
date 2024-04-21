package ex5;

public class Ex5 {

    public static void main(String[] args) {
        VowelThread object = new VowelThread();

        Thread t1 = new Thread(object);
        Thread t2 = new Thread(object);

        t1.setName("Primer");
        t2.setName("Segon");

        t1.start();
        t2.start();
    }

}
