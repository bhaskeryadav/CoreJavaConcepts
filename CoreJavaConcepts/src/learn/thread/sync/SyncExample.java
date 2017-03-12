package learn.thread.sync;

public class SyncExample {
	private  int count;
	
	public static void main(String[] args) {
		SyncExample sE=new SyncExample();
		sE.doWork();
	}

	private void doWork() {
		Thread t=new Thread(new  Runnable() {
			public void run() {
				for(int i=0;i<10000;i++){
					increment();
				}
			}
		});
		
		Thread t2=new Thread(new  Runnable() {
			public void run() {
				for(int i=0;i<10000;i++){
					increment();
				}
			}
		});
		
		t.start();
		t2.start();
		
		try {
			t.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(count);
		
	}
	private synchronized void increment() {
		count++;
	}
}
