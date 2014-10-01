package project.osms.presentation;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import project.osms.business.BusinessException;
import project.osms.business.Category;
import project.osms.business.CategoryController;
import project.osms.business.CategorySearchOptions;
import project.osms.business.Product;
import project.osms.business.ProductController;

@ManagedBean
public class NewProduct {
	
	Product product;
	private List<Category> categories;
	Integer categoryId;
	
	
	public NewProduct() {
		product = new Product();
		CategoryController controller = new CategoryController();
		setCategories(controller.searchCategories(new CategorySearchOptions()));
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		CategoryController controller = new CategoryController();
		this.product.setCategory(controller.selectCategory(categoryId, Category.class));
	}
	public String saveProduct(){
		FacesMessage message = new FacesMessage();
		ProductController controller = new ProductController();
		try{
		controller.saveProduct(product);
		
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Product Saved Sucessfully!");
		
		}catch(BusinessException e){
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary(e.getMessage());
		}
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		
		return "";
	}
	
}
