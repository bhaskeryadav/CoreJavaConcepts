package learn.thread.intercommunication;

import java.util.Scanner;

/**
 * @author KochiKaMama
 *
 */
public class InterThreadCommunication {

	public static void main(String[] args) throws InterruptedException {
		Worker w=new Worker();
		Thread t1=new Thread(new Runnable() {
			public void run() {
				try {
					w.produce(1);
				} catch (InterruptedException e) {
				}
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			public void run() {
				try {
					w.consume();
				} catch (InterruptedException e) {
				}
			}
		});
		
		Thread t3=new Thread(new Runnable() {
			public void run() {
				try {
					w.produce(2);
				} catch (InterruptedException e) {
				}
			}
		});
		
		
		t1.start();
		t3.start();
		t2.start();
		
		t1.join();
		t3.join();
		t2.join();
	}

}

class Worker {
	
	public void produce(int i) throws InterruptedException{
		synchronized (this) {
			System.out.println("produce started... "+i);
			wait();
			System.out.println("resuming... "+i);
		}
	}
	
	public void consume() throws InterruptedException{
		Thread.sleep(100);
		synchronized (this) {
			Scanner in=new Scanner(System.in);
			System.out.println("Hit return to resume produce");
			in.nextLine();
			notifyAll();
			System.out.println("enter key pressed");
		}
	}
	
}
