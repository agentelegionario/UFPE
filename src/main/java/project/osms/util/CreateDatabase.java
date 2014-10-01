package project.osms.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import project.osms.business.Category;
import project.osms.business.CategoryController;
import project.osms.business.Product;
import project.osms.business.ProductController;
import project.osms.persistence.CategoryDao;
import project.osms.persistence.EntityDao;
import project.osms.persistence.EntityManagerFactoryHolder;
import project.osms.persistence.ProductDao;

public class CreateDatabase {
	
	public static void main(String[] args) {
		EntityManagerFactory factory = EntityManagerFactoryHolder.factory;
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		EntityDao<Category> dao = new CategoryDao<>();
		EntityDao<Product> pDao = new ProductDao<>();
		Category category  = new Category();
		category.setName("CDS5");
		dao.insert(category);
		
		Category category1 = new Category();
		category1.setName("DVDS5");
		category1.setSuperCategory(category);
		dao.insert(category1);
		
		Category category2 = new Category();
		category2.setName("ROUPAS5");
		category2.setSuperCategory(category);
		dao.insert(category2);
		
		Product product = new Product();
		product.setName("Produto4");
		product.setDescription("Descrição do Produto1");
		product.setPrice(444.0);
		product.setCategory(category2);
		pDao.insert(product);
		
		transaction.commit();
		//EntityDao<Category> dao = new CategoryDao<>();
//		Category category  = new Category();
//		category.setName("CDS5");
//		dao.insert(category);
		
		CategoryController controller = new CategoryController();
		Category category3 = controller.selectCategory(17, Category.class);
		System.out.println(category3);
		
		/*
		ProductController pController = new ProductController();
		System.out.println(pController.selectProduct());
		
		transaction.begin();
		Category category  = new Category();
		category.setName("Ferramentas");
		manager.persist(category);
		transaction.commit();
		
		
		TypedQuery<Category> query = manager.createQuery("select c from Category c where lower(c.name) = :name",
							Category.class);
		query.setParameter("name", "lar");
		List<Category> result = query.getResultList();
		System.out.println(result);
		*/
		
	}
}
