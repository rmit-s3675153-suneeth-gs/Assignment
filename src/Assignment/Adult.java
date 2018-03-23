package Assignment;

import java.util.ArrayList;

public class Adult extends Person {
	private String friend;
	//ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
	public Adult(String Name, int Age, String Photo, String Status, int Key) {
		super(Name, Age, Photo, Status, Key);
	}
	
	public void setfriend(String f) {
		friend=f;
	}
	public String getfriend() {
		return friend;
	}
}
