package learn.thread.sync;

import java.util.ArrayList;
import java.util.List;

public class SyncBlockExample {
	public static void main(String[] args) throws InterruptedException {
		new WorkerThread().execute();
	}
}

class WorkerThread {
	/*
	 * run it multiple time by removing synchronized block and we'll get error.
	 * block synchronized doesn't acquires lock at object level hence multiple
	 * threads can execute various methods of the same class at same time We can
	 * use list1, and list2 as locks but we should avoid it to avoid confusion
	 */
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();

	private Object lock1 = new Object();
	private Object lock2 = new Object();

	public void stageOne() throws InterruptedException {
		synchronized (lock1) {
			Thread.sleep(1);
			list1.add(1);
		}
	}

	public void stageTwo() throws InterruptedException {
		synchronized (lock2) {
			Thread.sleep(1);
			list2.add(1);
		}
	}

	public void proccess() throws InterruptedException {
		for (int i = 0; i < 1000; i++) {
			stageOne();
			stageTwo();
		}
	}

	public void execute() throws InterruptedException {

		long start = System.currentTimeMillis();

		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					proccess();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					proccess();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();

		t1.join();
		t2.join();

		long end = System.currentTimeMillis();

		System.out.println("Total time : " + (end - start));
		System.out.println("size List1 : " + list1.size() + "\n list2 :" + list2.size());

	}
}
