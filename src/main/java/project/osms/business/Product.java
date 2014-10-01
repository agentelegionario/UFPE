package project.osms.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="PRO_PRODUCT")
public class Product implements Cloneable{
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String image;
	private Category category;
	
	@Id
	@Column(name="PRO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Size(min=1, max=200, message="Product's Name must have between 1 and 200 characters")
	@Column(name="PRO_NM", unique=true)
	public String getName() {
		return name;
	}
	@Column(name="PRO_DS")
	public String getDescription() {
		return description;
	}
	@Column(name="PRO_PRICE")
	public Double getPrice() {
		return price;
	}
	@Column(name="PRO_IMAGE")
	public String getImage() {
		return image;
	}
	@ManyToOne
	public Category getCategory() {
		return category;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public Product clone(){
		try {
			return (Product)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError("A VM está Louca");
		}
	}

}
