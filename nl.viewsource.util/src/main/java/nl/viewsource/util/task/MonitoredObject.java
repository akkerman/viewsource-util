package nl.viewsource.util.task;

interface MonitoredObject {
	int getTotalWork();
	int getProgress();
    void start();
	void stop();
	String getMessage();
	boolean isDone();
}
