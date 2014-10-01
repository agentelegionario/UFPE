package project.osms.presentation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import project.osms.business.BusinessException;
import project.osms.business.Category;
import project.osms.business.CategoryController;
import project.osms.business.CategorySearchOptions;

@ManagedBean
@SessionScoped
public class SearchCategory{
	
	Category category;
	Integer categoryId;
	List<Category> categories;
	List<Category> result;
	CategorySearchOptions options;
	boolean categoryDeleted = false;
	
	public SearchCategory() {
		reset();
	}
	private void reset() {
		category = new Category();
		options = new CategorySearchOptions();
		result = null;
	}
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		CategoryController controller =new CategoryController();
		category.setSuperCategory(controller.selectCategory(categoryId, Category.class));
		
	}
	public List<Category> getCategories() {
		CategoryController controller = new CategoryController();
		categories = controller.searchCategories(new CategorySearchOptions());

		Collections.sort(categories, new Comparator<Category>() {
	       	@Override
			public int compare(Category o1, Category o2) {
				 return  o1.getName().compareTo(o2.getName());
			}
	    });
		
		System.out.println(category);
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public boolean getCategoryDeleted() {
		return categoryDeleted;
	}
	public void setCategoryDeleted(boolean categoryDeleted) {
		this.categoryDeleted = categoryDeleted;
	}
		
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}	
	public List<Category> getResult() {
		return result;
	}
	public void setResult(List<Category> result) {
		this.result = result;
	}
	public CategorySearchOptions getOptions() {
		return options;
	}
	public void setOptions(CategorySearchOptions options) {
		this.options = options;
	}
	public void search(){
		options.setCategory(categoryId);
		CategoryController controller = new CategoryController();
		result = controller.searchCategories(options);
		System.out.println(result);
	}
	public String updateCategoryPage(Category colCategory){
		//System.out.println(colCategory);
		Category copyCategory = colCategory.clone();
		if(copyCategory.getSuperCategory() != null){
			categoryId = copyCategory.getSuperCategory().getId();
		}else{
			categoryId = null;
		}
		this.category = copyCategory;
		return "updateCategory";
	}
	public String  deleteCategoryPage(Category ColCategory){
		this.category = ColCategory;
		if(ColCategory.getSuperCategory() != null){
			categoryId = ColCategory.getSuperCategory().getId();
		}else{
			categoryId = null;
		}
		this.categoryDeleted = false;
		return "deleteCategory";
	}
	public void confirmDeletionCategory(){
		FacesMessage message = new FacesMessage();
		CategoryController controller = new CategoryController();
		try{
			controller.deleteCategory(category, Category.class, category.getId());		
			message.setSummary("Category removed sucessfully!");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			this.categoryDeleted = true;
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		reset();
	}
	public void updateCategory(){
		FacesMessage message = new FacesMessage();
		CategoryController controller = new CategoryController();
		try{
			controller.updateCategory(category);
			message.setSummary("Category Updated Sucessfully");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch(BusinessException e){
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
	}

}
