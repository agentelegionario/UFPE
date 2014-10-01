package project.osms.presentation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import project.osms.business.Category;
import project.osms.business.CategoryController;
import project.osms.business.CategorySearchOptions;

@ManagedBean
public class NewCategory {
	
	Category category;
	List<Category> categories;
	Integer categoryId;
	
	public NewCategory() {
		category = new Category();
		CategoryController controller = new CategoryController();
		categories = controller.searchCategories(new CategorySearchOptions());
		System.out.println(categories);
//		Collections.sort(categories, new Comparator<Category>() {
//	       	@Override
//			public int compare(Category o1, Category o2) {
//				 return  o1.getName().compareTo(o2.getName());
//			}
//	    });
	}
	
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		CategoryController controller = new CategoryController();
		this.category.setSuperCategory(controller.selectCategory(categoryId, Category.class));
	}
	public void saveCategory(){
		FacesMessage message = new FacesMessage();
		CategoryController controller = new CategoryController();
		try{
		controller.saveCategory(category);
		
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Category Saved Sucessfully!");
		
		}catch(RuntimeException e){
			System.out.println(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("Category name Already exists!");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
	}
	
}
