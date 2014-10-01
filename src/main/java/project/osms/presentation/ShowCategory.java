package project.osms.presentation;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.h2.util.New;

import project.osms.business.Category;
import project.osms.business.CategoryController;
import project.osms.business.CategorySearchOptions;
import project.osms.business.Product;
import project.osms.business.ProductController;
import project.osms.business.ProductSearchOptions;

@ManagedBean
public class ShowCategory {
	
	private Integer categoryId;
	private Category category;
	private List<Category> categories;
	private CategorySearchOptions options;
	
	public ShowCategory() {
		options = new CategorySearchOptions();
	}	
	public Integer getCategoryId() {
		return categoryId;
	}
	public Category getCategory() {
		return category;
	}
	public List<Category> getCategories() {
		options.setCategory(categoryId);
		CategoryController controller = new CategoryController();
		categories = controller.searchCategories(options);
		return categories;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		CategoryController controller = new CategoryController();
		this.category = controller.selectCategory(categoryId, Category.class);
	}
	public void setCategory(Category category) {
		this.category = category;
		
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public Integer getSuperCategory(){
		if(category != null && category.getSuperCategory() != null){
			return category.getSuperCategory().getId();
		}else{
			return 0;
		}
	}
	public void setSuperCategory(){
		
	}
	public List<Product> productsCategory(){
		ProductSearchOptions pOptions = new ProductSearchOptions();
		pOptions.setCategory(category);
		ProductController controller = new ProductController();
		return controller.searchProducts(pOptions);
	}

}
