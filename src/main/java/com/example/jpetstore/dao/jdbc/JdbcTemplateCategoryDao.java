package com.example.jpetstore.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class JdbcTemplateCategoryDao implements CategoryDao {
  
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SELECT_CATEGORY_LIST_SQL = "select CATID, NAME, DESCN from CATEGORY";
	private static final String SELECT_CATEGORY_SQL = "select CATID, NAME || ' (by JdbcTemplate)' as NAME, DESCN "
													+ "from CATEGORY where CATID = ?";

	public List<Category> getCategoryList() {
		List<Category> list = jdbcTemplate.query(
				SELECT_CATEGORY_LIST_SQL,
				new RowMapper<Category>() {
					public Category mapRow(ResultSet rs, int rowNum)throws SQLException {
						Category category = new Category();
						category.setCategoryId(rs.getString("CATID"));
						category.setName(rs.getString("NAME"));
						category.setDescription(rs.getString("DESCN"));
						return category;
					}
				});
		return list;	  
	}

	public Category getCategory(String categoryId) {
		Category category = jdbcTemplate.queryForObject(
				SELECT_CATEGORY_SQL,				 
				new RowMapper<Category>() {
					public Category mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Category category = new Category();
						category.setCategoryId(rs.getString("CATID"));
						category.setName(rs.getString("NAME"));
						category.setDescription(rs.getString("DESCN"));
						return category;
					}
				}, categoryId);
		return category;	 
	}

	private static final String SELECT_COUNT_SQL = "select count(*) from CATEGORY";
	public int getNumberOfCategories() {
		return jdbcTemplate.queryForObject(SELECT_COUNT_SQL, Integer.class);
	}

 	private static final String INSERT_SQL = "insert into CATEGORY (CATID, NAME, DESCN) values (?, ?, ?)";
	public void insertNewCategory(Category category) {
		jdbcTemplate.update(INSERT_SQL,
			new Object[] { category.getCategoryId(), category.getName(), category.getDescription() });
	}
}
