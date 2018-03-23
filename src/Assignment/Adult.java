package Assignment;
//vishal
import java.util.ArrayList;

public class Adult extends Person implements Children {
	private  ArrayList<String>child = new ArrayList<String>();
	private int cchild;
	public Adult(String Name, int Age, String Photo, String Status, int Key,int cchild) {
		super(Name, Age, Photo, Status, Key);//giving data to super class
		this.cchild=cchild;
	}
	
	public void setChild(String f) {//setters
		child.add(f);
	}
	public ArrayList<String> getChild() {//getters
		return child;
	}
	public int _delete_child(){
		child.clear();
		cchild=0;
		return cchild;
	}
	public int getcount(){
		return cchild;
	}
	public int incrementChild() {
		return cchild++;
	}
}
