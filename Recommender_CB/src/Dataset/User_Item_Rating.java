package Dataset;

public class User_Item_Rating {
	
	private int user,item,rating;
	
	public User_Item_Rating(){
		super();
	}
	
	public User_Item_Rating(int user, int item, int rating){
		this.user = user;
		this.item = item;
		this.rating = rating;
	}
	
	public int getUser(){
		return user;
	}
	
	public int getItem(){
		return item;
	}
	
	public int getRating(){
		return rating;
	}
	
	public void setUser(int user){
		this.user = user;
	}
	
	public void setItem(int item){
		this.item = item;
	}
	
	public void setRating(int rating){
		this.rating = rating;
	}

}
