package ex2;


public class Ex2 implements Runnable {
    String name;
    int number;
    static int factorialResult;

    public Ex2(String name, int number) {
        this.name = name;
        this.number = number;
        factorialResult = 0;
    }

    public void run() {
        int result = calculateFactorial(this.number);
        if (result != 1) {
            System.out.println("Fil: " + this.name + " | factorial of " + this.number + " : " + result);
        } else {
            System.out.println("Error calculating the factorials. Look the code bobo.");
        }
    }

    public int calculateFactorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            int result = 1;
            for (int i = 1; i <= number; i++) {
                result *= i;
            }
            factorialResult += result;
            return result;
        }
    }

    public static void main(String[] args) {
        try {
            Ex2 obj1 = new Ex2("first thread", 10);
            Ex2 obj2 = new Ex2("second thread", 5);

            Thread firstThread = new Thread(obj1);
            Thread secondThread = new Thread(obj2);

            firstThread.start();
            secondThread.start();

            firstThread.join();
            secondThread.join();

            if (factorialResult == 0) {
                System.out.println("Error getting the total, wtf");
            } else {
                System.out.println("Suma Total dels factorials: " + factorialResult);
            }

            System.out.println("Final del fil principal");
        } catch (InterruptedException e) {
            System.out.println("Error | " + e.getMessage());
            e.printStackTrace();
        }
    }
}
