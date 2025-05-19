package com.example.jpetstore.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="PRODUCT")  // 생략 가능
public class Product implements Serializable {

  /* Private Fields */
  @Id
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

  /* Public Methods*/

  public String toString() {
    return getName();
  }
}
