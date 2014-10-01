package project.osms.business;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EntitySearchOptions {

	protected String name; //representa a posição 0 de parameters
	protected String description;
	protected Double price;
	protected Integer id;
		
	protected String jpql;
	protected Integer numberOfparameters = 0;
	protected Class classe;
	protected Object[] parameters;
	
	
	public String getDescription() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	public Integer getId() {
		return id;
	}
	
	public Integer getNumberOfparameters() {
		return numberOfparameters;
	}
	public void setNumberOfparameters(Integer numberOfparameters) {
		this.numberOfparameters = numberOfparameters;
	}
	public Object[] getParameters() {
		return parameters;
	}
	public void setParameters(String[] parameters) {
		this.parameters = parameters;
	}
	public String getName() {
		return name;
	}
	public abstract void setName(String name);
	
	public String getJpql() {
		return jpql;
	}
	public void setJpql(String jpql) {
		this.jpql = jpql;
	}
	public Class getClasse() {
		return classe;
	}
	public void setClasse(Class classe) {
		this.classe = classe;
	}
	public abstract void JpqlStringBuilder();
	
	@Override
	public String toString() {
		return "EntitySearchOptions [name=" + name + ", description="
				+ description + ", price=" + price + ", id=" + id + ", jpql="
				+ jpql + ", numberOfparameters=" + numberOfparameters
				+ ", classe=" + classe + ", parameters="
				+ Arrays.toString(parameters) + "]";
	}
	
	
	
	
}
