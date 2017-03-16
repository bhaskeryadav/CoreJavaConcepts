package learn.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author KochiKaMama
 *
 */
public class AccessPrivateProperty {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor[] declaredConstructor = TestClass.class.getDeclaredConstructors();
		System.out.println(declaredConstructor.length);
		declaredConstructor[0].setAccessible(true);
		TestClass newInstance = (TestClass) declaredConstructor[0].newInstance();
		System.out.println(newInstance);
		
		 Method[] declaredMethods = TestClass.class.getDeclaredMethods();
		 declaredMethods[0].setAccessible(true);
		 declaredMethods[0].invoke(newInstance, null);
	}
}

class TestClass {
	private TestClass() {

	}
	
	private TestClass(String val){
		System.out.println(val);
	}
	
	private void sayHello(){
		System.out.println("Hi");
	}
}
