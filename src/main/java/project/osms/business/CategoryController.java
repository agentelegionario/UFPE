package project.osms.business;

import java.util.List;

import project.osms.persistence.CategoryDao;
import project.osms.persistence.EntityDao;

public class CategoryController{

	private  EntityDao<Category> dao;
	private Category category;
	
	public CategoryController() {
		dao = new CategoryDao<Category>();
	}

	public Category getCategory() {
		return category;
	}
	public EntityDao<Category> getDao() {
		return dao;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setDao(EntityDao<Category> dao) {
		this.dao = dao;
	}
	
	public void saveCategory(Category category) {
		dao.insert(category);		
	}
	public List<Category> searchCategories(CategorySearchOptions options) {
		return dao.selectList(options);
	}
	public Category selectCategory(Integer id, Class<Category> classe ){
		return dao.select(id, classe);
	}

	public void deleteCategory(Category category, Class<Category> entityClass,
			Integer idCategory) {
		try{
			dao.remove(category, entityClass, idCategory);
		}catch(RuntimeException e){
			throw new BusinessException(e.getMessage());
			 
		}
	}
	public void updateCategory(Category category) {
		try{
			dao.update(category);
		}catch(RuntimeException e){
			throw new BusinessException(e.getMessage());
		}
	}
	
		
	}

