package boggle;

public class Timer {
    private boolean isStarted;
    private int secondsLeft;

    public Timer() {
        this.isStarted = false;
        this.secondsLeft = 0;
    }

    public int getSecondsLeft() {
        return this.secondsLeft;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void countDown() {
        this.secondsLeft -= 1;
    }

    public void start() {
        this.isStarted = true;
    }

    public void setSeconds(int secondsLeft) {
        this.secondsLeft = secondsLeft;
    }
}