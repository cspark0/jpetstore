package com.example.jpetstore.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.dao.mybatis.mapper.CategoryMapper;
import com.example.jpetstore.domain.Category;

@Repository
public class MybatisCategoryDao implements CategoryDao {
	@Autowired
	private CategoryMapper categoryMapper;
	
	public List<Category> getCategoryList() throws DataAccessException {
		return categoryMapper.getCategoryList();
	}

	public Category getCategory(String categoryId) throws DataAccessException {
		return categoryMapper.getCategory(categoryId);
	}
}
