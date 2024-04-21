package ex4;

public class Ex4 implements Runnable {
    public static void main(String[] args) {
        ThreadGroup group = new ThreadGroup("CalculationGroup");
        int addResult = 0;
        int multiplyResult = 1;

        AddThread addThread = new AddThread(addResult);
        Thread addThreadInstance = new Thread(group, addThread);
        addThreadInstance.start();

        MultiplyThread multiplyThread = new MultiplyThread(multiplyResult);
        Thread multiplyThreadInstance = new Thread(group, multiplyThread);
        multiplyThreadInstance.start();

        try {
            Thread.sleep(3000);
            group.interrupt();

            addThreadInstance.join();
            multiplyThreadInstance.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("Addition result: " + addThread.getResult());
        System.out.println("Multiplication result: " + multiplyThread.getResult());
    }

    @Override
    public void run() {
        new Thread(new Ex4()).start();
    }
}
