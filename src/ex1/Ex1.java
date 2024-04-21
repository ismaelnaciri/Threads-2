package ex1;

public class Ex1 implements Runnable {
    String name;
    int number;
    static int factorialResult;

    public Ex1(String name, int number) {
        this.name = name;
        this.number = number;
        factorialResult = 1;
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
            return result;
        }
    }

    public static void main(String[] args) {
        Ex1 obj1 = new Ex1("first thread", 10);
        Ex1 obj2 = new Ex1("second thread", 5);

        Thread firstThread = new Thread(obj1);
        Thread secondThread = new Thread(obj2);

        firstThread.start();
        secondThread.start();

        System.out.println("Final del fil principal");
    }
}
