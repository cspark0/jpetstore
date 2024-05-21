package com.example.jpetstore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Profile implements Serializable {
	@Id
	private String userid;

	@Column(name="favcategory") 
	private String favouriteCategoryId;
	@Column(name="langpref") 
	private String languagePreference;
	@Column(name="mylistopt") 
	private boolean listOption;
	@Column(name="banneropt") 
	private boolean bannerOption;
	
	@ManyToOne
	@JoinColumn(name="favcategory")	
	private Banner banner;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String id) {
		this.userid = id;
	}

	public String getFavouriteCategoryId() {
		return favouriteCategoryId;
	}

	public void setFavouriteCategoryId(String favouriteCategoryId) {
		this.favouriteCategoryId = favouriteCategoryId;
	}

	public String getLanguagePreference() {
		return languagePreference;
	}

	public void setLanguagePreference(String languagePreference) {
		this.languagePreference = languagePreference;
	}

	public boolean isListOption() {
		return listOption;
	}

	public void setListOption(boolean listOption) {
		this.listOption = listOption;
	}

	public int getListOptionAsInt() {
		return listOption ? 1 : 0;
	}

	public boolean isBannerOption() {
		return bannerOption;
	}

	public void setBannerOption(boolean bannerOption) {
		this.bannerOption = bannerOption;
	}

	public int getBannerOptionAsInt() {
		return bannerOption ? 1 : 0;
	}
	
	public Banner getBanner() {
		return banner;
	}

	public void setBanner(Banner banner) {
		this.banner = banner;
	}

}
