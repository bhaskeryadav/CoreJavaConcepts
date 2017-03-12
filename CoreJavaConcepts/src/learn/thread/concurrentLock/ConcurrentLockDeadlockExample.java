package learn.thread.concurrentLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentLockDeadlockExample {
	
	public static void main(String[] args) {
		Worker w=new Worker();
		Thread t1=new Thread(new Runnable() {
			public void run() {
				try {
					w.firstWork();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread t2=new Thread(new Runnable() {
			public void run() {
				try {
					w.secondWork();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();
		
		w.printTotalBalance();
	}
}

class Worker {
	private Account a = new Account();
	private Account b = new Account();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();

	private void acquireLock(Lock lock1, Lock lock2) {
		boolean tryLock = false, tryLock2 = false;
		while (true) {
			try {
				tryLock = lock1.tryLock();
				tryLock2 = lock2.tryLock();

			} finally {
				if (tryLock && tryLock2) {
					return;
				}
				if (tryLock) {
					lock1.unlock();
				}
				
				if(tryLock2){
					lock2.unlock();
				}
			}
		}

	}

	public void firstWork() throws InterruptedException {
		acquireLock(lock1, lock2);
		Account.transfer(a, b, 100);
		lock1.unlock();
		lock2.unlock();
	}

	public void secondWork() throws InterruptedException {
		acquireLock(lock2, lock1);
		Account.transfer(b, a, 200);
		lock2.unlock();
		lock1.unlock();
	}

	public void printTotalBalance() {
		System.out.println((a.getBal()+b.getBal()));
	}

}

class Account {
	private int balance = 1000;

	public static void transfer(Account ac1, Account ac2, int amt) {
		ac1.balance -= amt;
		ac2.balance += amt;
	}

	public int getBal() {
		return balance;
	}
}
