package Assignment;//Suneeth

import java.util.ArrayList;

public abstract class Person {
	private String Name;
	private int Age;
	private String Photo;
	private String Status;
	private int Key;
	private ArrayList<String> friends= new ArrayList<String>();
	public Person(String Name,int Age,String Photo,String Status,int Key){//constructor fetching data
		this.Name=Name;
		this.Age=Age;
		this.Photo=Photo;
		this.Status=Status;
		this.Key=Key;
	}
	public void setName(String s){//setters to fetch data
		Name=s;
	}
	public void setKey(int a){
		Key=a;
	}
	public void setAge(int a){
		Age=a;
	}
	public void setPhoto(String p){
		Photo=p;
	}
	public void setStatus(String s){
		Status=s;
	}
	public String getName(){//getters returning data
		return Name;
	}
	public int getKey(){
		return Key;
	}
	public String getPhoto(){
		return Photo;
	}
	public int getAge(){
		return Age;
	}
	public String getStatus(){
		return Status;
	}
	
	
	
	

}
