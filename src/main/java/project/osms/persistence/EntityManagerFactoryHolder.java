package project.osms.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryHolder {

	public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("OSMS"); 
}
