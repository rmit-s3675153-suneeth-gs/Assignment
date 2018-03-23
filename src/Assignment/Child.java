package Assignment;
//vishal
import java.util.ArrayList;

public class Child extends Person implements parent {
	private ArrayList<String> parent = new ArrayList(2);

	public Child(String Name, int Age, String Photo, String Status, int Key,String parent1,String parent2) {
		super(Name, Age, Photo, Status, Key);// Super class constructor storing data
		parent.add(parent1);
		parent.add(parent2);//adding data into arraylist from constructor
	}
	public void setParent(String p1,String p2) {
		parent.add(p1);
		parent.add(p2);//adding data into arraylist from execution
	}
	public String getParent1(){
		return parent.get(0);//returning parent name
	}
	public String getParent2(){
		return parent.get(1);
	}

}
