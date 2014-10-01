package project.osms.presentation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import project.osms.business.BusinessException;
import project.osms.business.Category;
import project.osms.business.CategoryController;
import project.osms.business.CategorySearchOptions;
import project.osms.business.Product;
import project.osms.business.ProductController;
import project.osms.business.ProductSearchOptions;

@ManagedBean
@SessionScoped
public class SearchProduct {

	private Product product;
	private List<Product> result;
	private Integer categoryId;
	private List<Category> categories;
	private ProductSearchOptions options;


	public SearchProduct() {
		product = new Product();
		CategoryController controller = new CategoryController();
		options = new ProductSearchOptions();
		categories = controller.searchCategories(new CategorySearchOptions());
	}
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	public Product getProduct() {
		return product;
	}
	public List<Product> getResult() {
		return result;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setResult(List<Product> result) {
		this.result = result;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
		CategoryController controller = new CategoryController();
		this.product.setCategory(controller.selectCategory(categoryId, Category.class));
		options.setCategory(product.getCategory());
	}
	public ProductSearchOptions getOptions() {
		return options;
	}

	public void setOptions(ProductSearchOptions options) {
		this.options = options;
	}

	public void search(){
		ProductController  controller = new ProductController();
		result = controller.searchProducts(options);
	}

	public String updateProductPage(Product product){
		this.product = product.clone();
		categoryId = product.getCategory().getId();
		return "updateProduct";
	}
	public void updateProduct(){
		FacesMessage message = new FacesMessage();
		try{
			ProductController controller = new ProductController();
			controller.updateProduct(product);
			message.setSummary("Blog was successfully updated");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
		}catch (BusinessException e) {
			message.setSummary(e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
	}
	public String deleteProductPage(Product product){
		this.product = product.clone();
		categoryId = product.getCategory().getId();
		return "deleteProduct";
	}
	
	
	
	
	public void orderByDesc(){
		ProductController  controller = new ProductController();
		result = controller.searchProducts(options);
		Collections.sort(result, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return  o1.getDescription().compareTo(o2.getDescription());
			}
		});
	}
	public void orderByName(){
		ProductController  controller = new ProductController();
		result = controller.searchProducts(options);
		System.out.println(this.result);
		Collections.sort(this.result, new Comparator<Product>() {
			
			@Override
			public int compare(Product o1, Product o2) {
				return  o1.getName().compareTo(o2.getName());
			}
		});
	}
	public void orderByCategory(){
		ProductController  controller = new ProductController();
		result = controller.searchProducts(options);
		Collections.sort(result, new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return  o1.getCategory().getName().compareTo(o2.getCategory().getName());
			}
		});
	}
}
