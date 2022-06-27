package tp4;

public class Category {
	
	private int categoryID;
	private String name;
	
	public Category(int categoryID, String name) {
		// TODO Auto-generated constructor stub
		this.categoryID = categoryID;
		this.name = name;
	}
	
	public int getCategoryID() {
		return categoryID;
	}
	
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID ;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name ;
	}
	
}
