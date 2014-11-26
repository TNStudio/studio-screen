package application;

import javafx.concurrent.Task;

public class BackgroundThread implements Runnable{
	Thread thread;
	Task<?> task;
	
	public BackgroundThread(Task<?> task) {
		thread = new Thread(task);
		thread.setDaemon(true);
		this.task = task;
	}

	@Override
	public void run() {
		task.run();		
	}

	public void start() {
		thread.start();
	}
}
