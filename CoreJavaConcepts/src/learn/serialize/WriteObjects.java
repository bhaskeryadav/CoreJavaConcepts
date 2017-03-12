package learn.serialize;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class WriteObjects {
	public static void main(String[] args) {
		Person p=new Person("Bhasker");
		
		try(FileOutputStream fos=new FileOutputStream("person.bin")){
			ObjectOutputStream os=new ObjectOutputStream(fos);
			os.writeObject(p);
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Object : "+p+" written to file");
	}
}
