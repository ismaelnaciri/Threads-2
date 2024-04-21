package ex4;

public class AddThread implements Runnable {
    private int result;

    AddThread(int initialValue) {
        this.result = initialValue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(1000);
                result++;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getResult() {
        return result;
    }
}
