package learn.thread.produceConsume;

import java.util.LinkedList;
import java.util.Random;

public class LowLevelSync {
	public static void main(String[] args) {
		Worker w = new Worker();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					w.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					w.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
	}
}

class Worker {

	private LinkedList<Integer> list = new LinkedList<>();
	private final Integer LIMIT = 10;
	private Object lock = new Object();
	private Random random = new Random();

	public void produce() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (list.size() == LIMIT) {
					System.out.println("waiting for space... "+list.size());
					lock.wait(1000);
				}
				list.add(random.nextInt(1000));
				lock.notify();
			}
		}
	}

	public void consume() throws InterruptedException {
		synchronized (lock) {
			while (true) {
				if (list.size() == 0) {
					System.out.println("Waiting for value.... "+list.size());
					lock.wait(1000);
				}
				System.out.println("Removed element :" + list.removeFirst());
				lock.notify();
				Thread.sleep(1000);
			}
		}
	}

}
