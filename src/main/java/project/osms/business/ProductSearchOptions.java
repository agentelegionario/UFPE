package project.osms.business;

import java.util.ArrayList;

public class ProductSearchOptions extends EntitySearchOptions{
		private Category category = null;
		
		
	public ProductSearchOptions() {
		super();
		this.parameters = new Object[3];
		this.classe = Product.class;
		this.jpql = "select p from Product p where 1=1";
		this.numberOfparameters = 0;
		this.name = null;
		this.description = null;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
		this.parameters[2] = category;
		JpqlStringBuilder();	
	}
	public void setName(String name) {
		this.name = name;
		this.parameters[0] = name;
		
		JpqlStringBuilder();		
	}
	public void setDescription(String description){
		this.description = description;
		this.parameters[1] = description;
		
		JpqlStringBuilder();
	}
	private String[] parms = {
			"select p from Product p where 1=1",
			" and lower(p.name) like :parm0",
			" and lower(p.description) like :parm1",
			" and p.category = :parm2"
	};
	public void JpqlStringBuilder(){
		/*
		if(this.name == null || this.name.length() == 0){
			this.jpql = "select p from Product p where 1=1";
			this.numberOfparameters = 0;
		}
		*/
		jpql = parms[0];
		int count = 0;
		if(this.name != null){
			jpql += parms[1];
			count ++;
		}
		if(this.description != null){
			jpql += parms[2];
			count ++;
		}
		if(this.category != null){
			jpql += parms[3];
			count ++;
		}
		numberOfparameters = count;
	}
	
}
