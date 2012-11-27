package nl.viewsource.util.task;

public abstract class MonitoredTask implements MonitoredObject, Runnable {

    protected int totalWork;
    protected int progress;
    protected boolean running;
    private Thread[] threads;
    private int maxThreads;


    public MonitoredTask() {
        this(1);
    }

    public MonitoredTask(int maxTreads) {
        this.maxThreads = maxTreads;
        threads = new Thread[maxThreads];
        running = false;
    }

    abstract protected void perform();


    public final int getTotalWork() {
        return totalWork;
    }

    public final int getProgress() {
        return progress;
    }

    public final void start() {
        if (!running) {
            for (int i = 0; i < maxThreads; i++) {
                threads[i] = new Thread(this, "Thread" + i);
                threads[i].start();
            }
            running = true;
        }
    }

    public final void run() {
        perform();
    }
}
