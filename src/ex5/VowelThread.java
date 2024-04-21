package ex5;

public class VowelThread implements Runnable {

    static String vowel;

    public VowelThread() {
         vowel = "A";
    }

    @Override
    public void run() {
        if (Thread.currentThread().getName().equals("Primer")) {
            primer();
        } else {
            segon();
        }
    }

    public void primer() {
        synchronized (this) {
            try {
                Thread.sleep(500);
                while (!vowel.equals("U")) {
                    System.out.println("Vowel 1 : " + vowel);
                    if (vowel.equals("A")) {
                        vowel = "E";

                    } else if (vowel.equals("E")) {
                        vowel = "I";
                    } else if (vowel.equals("I")) {
                        vowel = "O";
                    }
                    notify();
                    wait();
                }
                System.out.println("Vowel 1 : " + vowel);
            } catch (InterruptedException e) {
                System.out.println("Error | " + e.getMessage());
            }
        }
    }

    public void segon() {
        synchronized (this) {
            try {
                while (!vowel.equals("U")) {
                    System.out.println("Vowel 2 : " + vowel);
                    if (vowel.equals("E")) {
                        vowel = "I";
                    } else if (vowel.equals("O")) {
                        vowel = "U";
                    }
                    notify();
                    wait();
                }
            } catch (InterruptedException e) {
                System.out.println("Error | " + e.getMessage());
            }
        }
    }
}
