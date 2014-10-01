package project.osms.business;

public class CategorySearchOptions extends EntitySearchOptions{
	Category category;
	public CategorySearchOptions() {
		super();
		this.parameters = new Object[2];
		this.classe = Category.class;
		this.jpql = "select c from Category c where 1=1";
		this.numberOfparameters = 0;
		this.name = null;
		category = null;

	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Integer categoryId) {
		CategoryController controller = new CategoryController();
		this.category = controller.selectCategory(categoryId, Category.class);
		parameters[1] = category;
		JpqlStringBuilder();
	}

	public void setName(String name) {
		this.name = name;
		this.parameters[0] = name;
		JpqlStringBuilder();		
	}

	private String[] parms = {
			"select c from Category c where 1=1",
			" and lower(c.name) like :parm0",
			" and c.superCategory = :parm1"
	};	
	public void JpqlStringBuilder(){

		jpql = parms[0];
		int count = 0;
		if(this.name != null){
			jpql += parms[1];
			count ++;
		}
		if(this.category != null){
			jpql += parms[2];
			count ++;
		}
		numberOfparameters = count;
	}


}


