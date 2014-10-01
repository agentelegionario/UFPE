package project.osms.persistence;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import project.osms.business.Category;
import project.osms.business.EntitySearchOptions;


public abstract class EntityDao<E>{
	private EntityManagerFactory factory;
	
	public EntityDao(){
		factory = EntityManagerFactoryHolder.factory;
		
	}	
	
	public void insert(E entity) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try{
			System.out.println(entity);
			transaction.begin();
			manager.persist(entity);
			transaction.commit();
			System.out.println("OK");
		}catch(RuntimeException e){
			System.out.println("Insert " + e.getMessage());
			throw new RuntimeException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<E> selectList(EntitySearchOptions options) {
		EntityManager manager = factory.createEntityManager();
		TypedQuery<E> query = manager.createQuery(options.getJpql(),options.getClasse());
		System.out.println(options);
		System.out.println(options.getParameters().length);
		int i = 0;
		while(i < options.getParameters().length) {
			if (!(options.getParameters()[i] == null)){
				if(Classes.contains(options.getParameters()[i].getClass())){
					String parm = "parm" + i;
					query.setParameter(parm, options.getParameters()[i]);
				}else{
					
					String parm = "parm" + i;
					query.setParameter(parm, "%" + options.getParameters()[i].toString().toLowerCase() + "%");
				}
			}
			i++;
		}
		
		return query.getResultList();
	}
	public E select(Integer id, Class<E> classe){
		EntityManager manager = factory.createEntityManager();
		return manager.find(classe, id); 
	}

	public void remove(E entity, Class<E> entityClass, Integer idEntity) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		Object entityManaged = manager.find(entityClass, idEntity);
		transaction.begin();
		manager.remove(entityManaged);
		transaction.commit();
	}

	public void update(E entity) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transation = manager.getTransaction();
		transation.begin();
		manager.merge(entity);
		transation.commit();
		
	}
	
	
}

	