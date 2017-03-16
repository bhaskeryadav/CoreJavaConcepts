package learn.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author KochiKaMama
 * Use git history to see every possibility
 */
public class BreakSingleton {
	public static void main(String[] args) throws Exception {
		
		breakReflection();
		
	}

	private static void breakReflection()
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		Constructor<?>[] constructors = TestClass.class.getDeclaredConstructors();
		constructors[1].setAccessible(true);
		TestClass newInstance = (TestClass) constructors[1].newInstance("newIn");
		System.out.println(newInstance.val);
	}
}


class TestClass{
	public String val="default";
	
	private static TestClass tc;
	
	private TestClass() {

	}
	
	private TestClass(String v){
		val=v;
	}
	
	public static TestClass getInstance(){
		if(tc==null){
			tc=new TestClass("d");
		}
		return tc;
	}
}