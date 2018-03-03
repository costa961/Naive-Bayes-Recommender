package Bayes;

public class Item_Category_Probability {
	
	private String item,category;
	private float probability;
	
	public Item_Category_Probability(){
		super();
	}
	
	public String getItem(){
		return item;
	}
	
	public String getCategory(){
		return category;
	}
	
	public float getProbability(){
		return probability;
	}
	
	public void setItem(String item){
		this.item = item;
	}
	
	public void setCategory(String category){
		this.category = category;
	}
	
	public void setProbability(float probability){
		this.probability = probability;
	}

}
