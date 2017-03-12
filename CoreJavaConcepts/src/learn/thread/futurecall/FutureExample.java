package learn.thread.futurecall;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		Future<Integer> submit = executor.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				Random r=new Random();
				Thread.sleep(5000);
				return r.nextInt(1000);
			}
		});
		
		executor.shutdown();
		
		System.out.println("future value : "+submit.get());
		
		System.out.println("end of main");
		
	}
}
