package com.example.jpetstore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Product implements Serializable {

  /* Private Fields */
  @Id
  @Column(name="productid")
  private String productId;
  
  @Column(name="category")
  private String categoryId;
  
  private String name;
  
  @Column(name="descn")
  private String description;

  /* JavaBeans Properties */

  public String getProductId() { return productId; }
  public void setProductId(String productId) { this.productId = productId.trim(); }

  public String getCategoryId() { return categoryId; }
  public void setCategoryId(String categoryId) { this.categoryId = categoryId; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  @Override
  public String toString() {
	return "Product [productId=" + productId + ", categoryId=" + categoryId + ", name=" + name + ", description="
			+ description + "]";
  }
}
