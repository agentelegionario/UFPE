package project.osms.business;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import project.osms.persistence.CategoryDao;

public class CategoryTest {

	@Test
	public void teste1(){
		
		Category c = new Category();
		c.setName("Alcides");
		
		
		CategoryDao<Category> dao = EasyMock.createMock(CategoryDao.class);
		
		dao.insert(c);
			
		
		EasyMock.replay(dao);
		
		CategoryController contr = new CategoryController();
		contr.setDao(dao);
		contr.saveCategory(c);
		
		EasyMock.verify(dao);
		
	}
}
