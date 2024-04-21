package ex4;

public class MultiplyThread implements Runnable {
    private int result;

    MultiplyThread(int initialValue) {
        this.result = initialValue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);
                result *= 2;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getResult() {
        return result;
    }
}
