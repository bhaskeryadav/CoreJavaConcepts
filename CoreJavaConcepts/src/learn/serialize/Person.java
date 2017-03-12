package learn.serialize;

import java.io.Serializable;

public class Person implements Serializable{
	
	private String name;
	
	public Person(String n) {
		name=n;
	}
	
	@Override
	public String toString() {
		return "name : "+name;
	}
	
}
