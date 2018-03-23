package Assignment;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
	private  ArrayList<String>Users = new ArrayList<String>();
	private  ArrayList<Integer>Age = new ArrayList<Integer>();
	private  ArrayList<String>Status = new ArrayList<String>();
	private  ArrayList<String>Photo = new ArrayList<String>();
	private  ArrayList<Integer>Key = new ArrayList<Integer>();
	private  ArrayList<String>ChildParent = new ArrayList<String>();
	private static ArrayList<ArrayList<String>>friendList = new ArrayList<ArrayList<String>>();
	private ArrayList<ArrayList<Integer>>trackList = new ArrayList<ArrayList<Integer>>();
	private ArrayList<Integer> trackID = new ArrayList<Integer>();
	private static int value=0;
	Person[] list = new Person[1000];
	public void HardCodeData(){
		
		int i=4;
		list[value]  = new Adult("Suneeth",26,"xyz.ph","Studying at rmit",1,0);
		AddAdult(list[value] );value++;
		list[value]  = new Adult("James",40,"james.ph","Married",2,1);
		((Adult)list[value]).setChild("Joe");
		AddAdult(list[value] );
		value++;
		list[value]  = new Adult("Rose",36,"rose.ph","Married",3,1);
		AddAdult(list[value] );
		((Adult)list[value]).setChild("Joe");
		value++;
		list[value] = new Child("Joe",15,"joe.ph","Works at mac",4,"James","Rose");
		AddChild(list[value]);value++;
		ArrayList<String> temp2 = new ArrayList<String>();
		ArrayList<Integer> tempid2= new ArrayList<Integer>();
		temp2.add("James");
		temp2.add("Rose");
		tempid2.add(2);
		tempid2.add(3);
		friendList.add(temp2);
		trackList.add(tempid2);
		trackID.add(1);
		
		ArrayList<String> temp = new ArrayList<String>();
		ArrayList<Integer> tempid= new ArrayList<Integer>();
		temp.add("Rose");
		tempid.add(3);
		friendList.add(temp);
		trackList.add(tempid);
		trackID.add(2);
		
		ArrayList<String> temp1 = new ArrayList<String>();
		ArrayList<Integer> tempid1= new ArrayList<Integer>();
		tempid1.add(2);
		temp1.add("James");
		friendList.add(temp1);
		trackList.add(tempid1);
		trackID.add(3);
		
		ArrayList<String> temp3 = new ArrayList<String>();
		ArrayList<Integer> tempid3= new ArrayList<Integer>();
		temp3.add("James");temp3.add("Rose");
		tempid3.add(2);
		tempid3.add(3);
		friendList.add(temp3);
		trackList.add(tempid3);
		trackID.add(4);
	}
	public void AddAdult(Person list){
		Users.add(list.getName());
		Age.add(list.getAge());
		Status.add(list.getStatus());
		Photo.add(list.getPhoto());
		Key.add(list.getKey());
	}
	public void AddChild(Person list){
		Users.add(list.getName());
		Age.add(list.getAge());
		Status.add(list.getStatus());
		Photo.add(list.getPhoto());
		Key.add(list.getKey());
		//ChildParent.add(((Child) list).getParent1());
		//ChildParent.add(((Child) list).getParent2());
	}
	
	public ArrayList<String>ListUser(){
		System.out.println(Users);
		return Users;
	}
	private boolean checkParentsExists(String parent1,String parent2){
		int parent1exists=0,parent2exists=0;
		for(int i=0;i<Users.size();i++){
			if(parent1.equals(Users.get(i)))
				parent1exists++;
			if(parent2.equals(Users.get(i)))
				parent2exists++;
			if(parent1exists==1&&parent2exists==1)
				return true;
		}
		return false;
	}
	private void EnterChild(int choice,int New_key){
		int age;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Name");
		String name=sc.nextLine();
		System.out.println("Enter the Photo");
		String photo=sc.nextLine();
		System.out.println("Enter the Status");
		String status=sc.nextLine();
		System.out.println("Enter the name of Parent1");
		String parent1=sc.nextLine();
		System.out.println("Enter the name of Parent2");
		String parent2=sc.nextLine();
		do{
			System.out.println("Enter the Age");
			age = sc.nextInt();
		}while(age>16);
		if(checkParentsExists(parent1,parent2)==false){
			System.out.println("One or both of the parentsdoesnt exists, Please try again");
			EnterChild(choice,New_key);
		}
		if(checkParentsFriends(parent1,parent2)==false){
			System.out.println("Parents are not friends to each other, Please try again");
			EnterChild(choice,New_key);
		}
		else{
			trackID.add(New_key);
			friendList.add(null);
			trackList.add(null);
			int index=getTrackIDposition(New_key);
			
			addParentasFriend(New_key,name,parent1,KeyfromName(parent1));
			addParentasFriend(New_key,name,parent2,KeyfromName(parent2));
			((Adult) list[KeyfromName(parent1)-1]).setChild(name);
			((Adult) list[KeyfromName(parent1)-1]).incrementChild();
			((Adult) list[KeyfromName(parent2)-1]).setChild(name);
			((Adult) list[KeyfromName(parent2)-1]).incrementChild();
			
		}
	
		list[value]= new Child(name,age,photo,status,New_key,parent1,parent2);
		AddChild(list[value]);
		value++;
	}
	private void addParentasFriend(int new_key, String name, String parent,int parent_key) {
			int index = getTrackIDposition(new_key);
			System.out.println("James");
			friendAdd(index,parent,parent_key,new_key);
			System.out.println("Rose");
			index=getTrackIDposition(parent_key);
			friendAdd(index,name,new_key,parent_key);
	}
	private int KeyfromName(String name){
		int i;
		for(i=0;i<Users.size();i++)
			if(name.equals(Users.get(i)))
				break;
		return Key.get(i);
		
	}
	private int PosfromName(String name){
		int i;
		for(i=0;i<Users.size();i++)
			if(name.equals(Users.get(i)))
				break;
		return i;
		
	}
	private boolean checkParentsFriends(String parent1, String parent2) {
		int i;
		int Parent_Position = PosfromName(parent1);
		int index = Key.get(Parent_Position);
		for(i=0;i<trackID.size();i++)
			if(index==trackID.get(i))
				break;
		int tracked_Position=i;
		int parent_friend_exists=0;
		//System.out.println(friendList.get(tracked_Position).size());
		for(i=0;i<friendList.get(tracked_Position).size();i++)
			if(parent2.equals(friendList.get(tracked_Position).get(i))){
				parent_friend_exists++;
				break;
			}
		if(parent_friend_exists==1)
			return true;
		return false;
	}
	public int AddUser(){
		int New_key=Users.size()+1;
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to add a Child or Adult\nPress 1. child\nPress 2 . adult");
		int choice = sc.nextInt();
		if(choice ==1){
			EnterChild(choice,New_key);
			System.out.println(friendList+"ASD");
		}else if(choice==2){
			EnterAdult(choice,New_key);
		}else
			AddUser();
		
		return New_key;
		
	}
	private void EnterAdult(int choice, int new_key) {
		int age;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Name");
		String name=sc.nextLine();
		System.out.println("Enter the Photo");
		String photo=sc.nextLine();
		System.out.println("Enter the Status");
		String status=sc.nextLine();
		do{
			System.out.println("Enter the Age");
			age = sc.nextInt();
		}while(age<16);
		System.out.println("Enter Number of children");
		int number = sc.nextInt();
		ArrayList<String> _temp_child= new ArrayList<String>();
		for(int i=1;i<=number;i++){
			System.out.println("Enter the child");
			String chname = sc.nextLine();
			int childexists=0;
			for(int j=0;j<Users.size();j++){
				if(chname.equals(Users.get(j))){
					childexists++;
					((Adult) list[value]).setChild(chname);
					break;
				}
			}
			if(childexists==0){
				System.out.println("Entered child doesnt exists, Please enter a valid user");
				EnterAdult(choice,new_key);
			}
		}
		list[value]= new Adult(name,age,photo,status,new_key,number);
		
		AddAdult(list[value]);
		value++;
		
	}
	public void Menu(){
		int input=0;
		do{	System.out.println("MiniNet Menu");
		System.out.println("=================================");
			System.out.println("1.List everyone");
			System.out.println("2.Select a person");
			System.out.println("3.Add an User");
			System.out.println("4.Exit");
			Scanner sc = new Scanner(System.in);
			input=sc.nextInt();
			switch(input){
			case 1: ListUser();
					break;
			case 2: int id=SelectUser();
					break;
			case 3:	int key=AddUser();
					int j;		
					for(j=0;j<Key.size();j++){
						if(key==Key.get(j))
							break;
					}
					System.out.println(key);
					System.out.println(Age.get(j));
					if(Age.get(j)>16){
						trackID.add(key);
						friendList.add(null);
						trackList.add(null);
					}
					int in;
					do{
						System.out.println("1.Add friends");
						System.out.println("2.exit");
						in =sc.nextInt();
						switch(in){
						case 1:	if(Age.get(j)>16)
									addFriend(key,Users.get(j));
								else if(Age.get(j)>4&&Age.get(j)<=16){
									System.out.println(key+" "+Users.get(j)+" "+Age.get(j));
									addOtherDependent(key,Users.get(j),Age.get(j));}
								else
									System.out.println("UnderAge :Sorry cant have friends, come when you growup" );
								break;
						}
					}while(in<2);
					break;
			}
			
		}while(input!=4);
	}
	private void addOtherDependent(int key,String name,int age) {
		String name_depend;
		int key_dep,index;
		int check_flag=0;
		int flag=0;
		index=getTrackIDposition(key);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of your dependent friend");
		name_depend=sc.nextLine();
		System.out.println("Enter the key of your dependent friend");
		key_dep=sc.nextInt();
		
		int i;
		for(i=0;i<Users.size();i++)
			if(name_depend.equals(Users.get(i))){
				check_flag++;
				break;
			}
		if(check_flag==0){
			System.out.println("User doesnt exists");
			addOtherDependent(key,name,age);
		}
		if(Age.get(i)>16){
			System.out.println("The entered user is not a child ! Please try again!");
			addOtherDependent(key,name,age);
		}
		String Parent1=((Child) list[key-1]).getParent1();
		String Parent2=((Child) list[key-1]).getParent2();
		String _depParent1=((Child) list[key_dep-1]).getParent1();
		String _depParent2=((Child) list[key_dep-1]).getParent2();
		for(i=0;i<Users.size();i++)
			if(name_depend.equals(Users.get(i))&&key_dep==Key.get(i)){
				flag++;
				break;
			}
		int position_of_dep =i;
		int proceedflag=0;
		int dep_age=Age.get(position_of_dep);
		System.out.println(age+" "+dep_age);
		if(flag==0)
			System.out.println("That friend doesnt exists!, Please make sure he/she exists in the system");
		else{
			int age_upperlimit,age_lowerlimit;
			age_upperlimit=age+3;
			age_lowerlimit=age-3;
			if(age_upperlimit>16)
				age_upperlimit=16;
			if(age_lowerlimit<3)
				age_lowerlimit=3;
			
			if(dep_age<=age_upperlimit&&dep_age>=(age_lowerlimit))
					proceedflag++;
			if(proceedflag==1){
				System.out.println("Aasd");
				if(Parent1.equals(_depParent2)||Parent1.equals(_depParent1)||Parent2.equals(_depParent2)||Parent2.equals(_depParent1)){
					System.out.println("Parents Should be mutually exclusive");
					addOtherDependent(key,name,age);
				}
				else{
					friendAdd(index,name_depend,key_dep,key);
					index=getTrackIDposition(key_dep);
					friendAdd(index,name,key,key_dep);
				}
			}
		}
		
	}
	public int getTrackIDposition(int key){
		int j;
		for(j=0;j<trackID.size();j++)
			if(key==trackID.get(j))
				return j;
		return -1;
	}
	public void addFriend(int new_user_key,String new_user_name){
		Scanner sc = new Scanner(System.in);
		int flag=0,i;
		System.out.println("Enter your friends name");
		String friend_name =sc.nextLine();
		System.out.println("Enter your friends id");
		int friend_key= sc.nextInt();
		for(i=0;i<Users.size();i++){
			if(friend_name.equals(Users.get(i))&&friend_key==Key.get(i)){
				flag++;
				break;
			}
		}
		if(flag==0)
			System.out.println("Sorry: entered friend name doesnt exist, please try again");
		else{
				int index = getTrackIDposition(new_user_key);
				System.out.println();
				friendAdd(index,friend_name,friend_key,new_user_key);
				index=getTrackIDposition(friend_key);
				friendAdd(index,new_user_name,new_user_key,friend_key);
		}
		
	}
	public void friendAdd(int index,String name,int id,int key){
		if(friendList.get(index)==null){
			System.out.println(friendList.get(index));
			ArrayList<String> temp = new ArrayList();
			temp.add(name);
			ArrayList<Integer> tid = new ArrayList();
			tid.add(id);
			System.out.println(trackID);
			friendList.remove(index);
			trackList.remove(index);
			trackID.remove(index);
			friendList.add(temp);
			trackList.add(tid);
			trackID.add(key);
		}
		else{
			ArrayList<String> temp = new ArrayList();
			ArrayList<Integer> tid = new ArrayList();
			for(int j=0;j<friendList.get(index).size();j++){
				temp.add(friendList.get(index).get(j));
				tid.add(trackList.get(index).get(j));
			}
			temp.add(name);
			tid.add(id);
			friendList.remove(index);
			trackList.remove(index);
			trackID.remove(index);
			friendList.add(temp);
			trackList.add(tid);
			trackID.add(key);
		}
		
	}
	public int SelectUser(){
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the Username");
		String name =sc.nextLine();
		System.out.println("Enter the Key");
		int key = sc.nextInt();
		int flag=0,i,j;
		for(i=0;i<Users.size();i++){
			if(key==Key.get(i)&&name.equals(Users.get(i))){
				flag++;
				break;//takeage.get
			}
		}
		int age= Age.get(i);
		if(flag==0)
			SelectUser();
		int selection;
		do{	System.out.println("SubMenu");
			System.out.println();
			System.out.println("1.Add friends");
			System.out.println("2.show Parent/Child");
			System.out.println("3.Find Directfriend or not");
			System.out.println("4.delete account");
			System.out.println("5.display user ");
			System.out.println("6.exit");
			selection=sc.nextInt();
			switch(selection){
			case 1:	if(age>16)
						addFriend(key,name);
					else
						addOtherDependent(key,name,age);
					break;
			case 2: if(age<=16)
						list_Parent(key);
					else{
						int count=((Adult) list[key-1]).getcount();
						if(count==0)
							System.out.println("No Children exists");
						else
							list_Child(key);
					}
					break;
			case 3:	Findfriend(key);
					break;
			case 4: DeleteAccount(key);
					Menu();
					break;
			case 5: DisplayProfile(i);
					break;
			}
		}while(selection!=6);
		return 0;
	}
	public void UpdateProfile(){
		
	}
	public void DisplayProfile(int index){
		int j;
		System.out.println(Users.get(index));
		System.out.println(Key.get(index));
		System.out.println(Status.get(index));
		System.out.println(Age.get(index));
		System.out.println(Photo.get(index));
		for(j=0;j<trackID.size();j++){
			if(Key.get(index)==trackID.get(j))
				break;
		}
		System.out.println(friendList.get(j));
		
	}
	public void DeleteAccount(int id){
		int flag=0,i,j;
		for(i =0;i<Key.size();i++){
			if(id==Key.get(i)){
				flag++;
				break;
			}
		}
		int delete_position=i;
		String name=Users.get(delete_position);
		if(flag==0){
			System.out.println("Doesnt exist-Enter a valid id");
		}
		else{
			for(j=0;j<trackID.size();j++){
				if(Key.get(delete_position)==trackID.get(j))
					break;
			}
			for(int k =0;k<friendList.get(j).size();k++){
				String Delete_Friend_name =friendList.get(j).get(k);
				System.out.println(Delete_Friend_name);
				for(i=0;i<Users.size();i++)
					if(Delete_Friend_name.equals(Users.get(i)))
						break;
						
				int Delete_Friend_Position=i;
				System.out.println(Delete_Friend_Position);
				
				for(i=0;i<trackID.size();i++)
					if(Key.get(Delete_Friend_Position)==trackID.get(i))
						break;
				int friend_position_trackID =i;
				for(i=0;i<friendList.get(friend_position_trackID).size();i++){
					if(name.equals(friendList.get(friend_position_trackID).get(i))){
						friendList.get(friend_position_trackID).remove(i);
						break;
					}
				}
				
			}
			((Adult) list[Key.get(delete_position)-1])._delete_child();
			Users.remove(delete_position);
			Key.remove(delete_position);
			Status.remove(delete_position);
			Age.remove(delete_position);
			Photo.remove(delete_position);
			
		}
		
		
	}
	public void Findfriend(int key){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the friend you want to find ");
		String name = sc.nextLine();
		System.out.println("Enter the id");
		int id = sc.nextInt();
		int i,flag=0;
		for(i=0;i<trackID.size();i++){
			if(key==trackID.get(i)){
				break;
			}
		}
		int index=i;
		for(i=0;i<Users.size();i++){
			if(name.equals(Users.get(i))){
				flag++;
				break;
			}
		}
		if(flag==0){
			System.out.println("User doesnt exists !");
		}else{
			flag=0;
			for(i=0;i<friendList.get(index).size();i++){
				if(name.equals(friendList.get(index).get(i))){
					flag++;
					break;
				}
			}
			if(flag==1)
				System.out.println(name+" is a direct friend to "+Users.get(index));
			else
				System.out.println(name+" is not a direct friend to "+Users.get(index));
				
		}
		
	}
	public  void list_Parent(int key){
		System.out.println("THE PARENTS ARE : ");
		System.out.println(((Child)list[key-1]).getParent1());
		System.out.println(((Child)list[key-1]).getParent2());
		
	}
	public void list_Child(int key){
		ArrayList <String>t_child = new ArrayList<String>();
		t_child=((Adult)list[key-1]).getChild();
		for(int i=0;i<t_child.size();i++){
			System.out.println(t_child.get(i));
		}
		
	}
	//public void 

}
