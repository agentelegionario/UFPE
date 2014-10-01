package project.osms.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;



@Entity
@Table(name="CAT_CATEGORY")
public class Category implements Cloneable{
	
	private Integer id;
	private String name;
	private Category superCategory;
	private List<Category> categories;
	private List<Product> products;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAT_ID")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Size(min=1, max=100, message="Category's name must have between 1 and 100 characters!")
	@Column(name="CAT_NM", unique=true)
	public String getName() {
		return name;
	}
	/*@ManyToOne
	public Category getCategory() {
		CategoryDao<Category> dao = new CategoryDao<>();
		System.out.println("getCategory" + dao.select(id, Category.class));
		return dao.select(id, Category.class);
	}*/
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne
	@JoinColumn(name="superCategory_Id")
	public Category getSuperCategory() {
		return superCategory;
	}
	public void setSuperCategory(Category superCategory) {
		this.superCategory = superCategory;
	}
	@OneToMany(mappedBy="superCategory")
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	@OneToMany(mappedBy="category")
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public Category clone(){
		try {
			return (Category)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError("A VM est� Louca");
		}
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", superCategory="
				+ superCategory + "]";
	}
	
}
