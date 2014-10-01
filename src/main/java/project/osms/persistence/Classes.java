package project.osms.persistence;

import java.util.ArrayList;
import java.util.List;

import project.osms.business.Category;
import project.osms.business.Product;

public class Classes {
	
	private static List<Class> classes = new ArrayList<>();
	
	public static boolean contains(Class classe){
		classes.add(Category.class);
		classes.add(Product.class);
		
		return classes.contains(classe);
	}

}
