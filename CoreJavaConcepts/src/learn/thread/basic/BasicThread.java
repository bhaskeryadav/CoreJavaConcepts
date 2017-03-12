package learn.thread.basic;


/**
 * @author KochiKaMama
 *
 */
public class BasicThread {
	
	public static void main(String[] args) {
		TestThread thrd=new TestThread();
		thrd.start();
		// if we comment start then main sum ill be same as testtread
		//thrd.run();
		System.out.println("Main sum :"+thrd.sum);
	}
	
}

class TestThread extends Thread
{
	public int sum=0;
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			sum+=i;
		}
		
		System.out.println("TestThread sum : "+sum);
	}
}
