package learn.thread.basic;

/**
 * @author KochiKaMama
 *
 */
public class MultiThreadsYieldJoin {
	
	public static void main(String[] args) throws InterruptedException {
		MultiThrd t1=new MultiThrd("abc");
		MultiThrd t2=new MultiThrd("-------------");
		
		t1.start();
		t2.start();
		t2.join();
	}
	
}

class MultiThrd extends Thread{
	
	public String name;
	
	public MultiThrd(String n) {
		this.name=n;
	}
	
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.println(name+" i : "+i);
			
			if(i==77)
				yield();
		}
	}
}