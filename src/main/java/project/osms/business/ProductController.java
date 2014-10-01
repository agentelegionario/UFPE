package project.osms.business;

import java.util.List;

import project.osms.persistence.EntityDao;
import project.osms.persistence.ProductDao;

public class ProductController {

	private  EntityDao<Product> dao;
	private Product product;
	
	public ProductController() {
		dao = new ProductDao<Product>();
	}

	public Product getProduct() {
		return product;
	}
	public EntityDao<Product> getDao() {
		return dao;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setDao(EntityDao<Product> dao) {
		this.dao = dao;
	}
	
	public void saveProduct(Product product) {
		try{
			dao.insert(product);
		}catch(RuntimeException e){
			throw new BusinessException("Product name Already !");
		}
	}
	public List<Product> searchProducts(ProductSearchOptions options) {
		return dao.selectList(options);
	}

	public void deleteProduct(Product product, Class<Product> entityClass,
			Integer idProduct) {
		try{
			dao.remove(product, entityClass, idProduct);
		}catch(RuntimeException e){
			throw new BusinessException(e.getMessage());
			 
		}
	}
	public void updateProduct(Product product) {
		try{
			dao.update(product);
		}catch(RuntimeException e){
			throw new BusinessException(e.getMessage());
		}
	}
	
		
	}

