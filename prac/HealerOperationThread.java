package prac;

public class HealerOperationThread implements Runnable {

    @Override
    public void run() {
        Description.setOperationProbability();
    }
}
