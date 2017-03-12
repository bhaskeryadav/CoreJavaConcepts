package learn.thread.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 5; i++) {
			executor.submit(new WorkerThread(i));
		}
		executor.shutdown();

		System.out.println("All tasks are submitted");
		/*
		 * we'll get an exception here as the executor has been shutdown so no
		 * new task will be submitted
		 */
		// executor.submit(new WorkerThread(6));

		executor.awaitTermination(1, TimeUnit.MINUTES);

		System.out.println("All tasks complted");

	}
}

class WorkerThread implements Runnable {

	private int id;

	public WorkerThread(int id) {
		this.id = id;
	}

	public void run() {
		System.out.println("Staring .. " + id);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("completed .. " + id);
	}
}
