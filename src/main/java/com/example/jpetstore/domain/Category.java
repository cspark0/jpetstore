package com.example.jpetstore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="CATEGORY")  // 생략 가능
public class Category implements Serializable {

  /* Private Fields */

  @Id @Column(name="catid")
  private String categoryId;
  private String name;
  @Column(name="descn")
  private String description;

  /* JavaBeans Properties */

  public String getCategoryId() { return categoryId; }
  public void setCategoryId(String categoryId) { this.categoryId = categoryId.trim(); }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  /* Public Methods */

  public String toString() {
    return getCategoryId();
  }
}