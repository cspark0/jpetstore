package com.example.jpetstore.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.domain.Category;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
// import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class NamedParamJdbcTemplateCategoryDao implements CategoryDao {
  
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SELECT_CATEGORY_LIST_SQL = "select CATID, NAME, DESCN from CATEGORY";
	private static final String SELECT_CATEGORY_SQL = "select CATID, NAME || ' (by NamedParamJdbcTemplate)' as NAME, DESCN "
													+ "from CATEGORY where CATID = :catId";

	public List<Category> getCategoryList() {
		List<Category> list = jdbcTemplate.query(
			SELECT_CATEGORY_LIST_SQL,
			Collections.<String,Object>emptyMap(),
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
		Map<String, String> params = new HashMap<String, String>();
		params.put("catId", categoryId);
		// 또는
		// MapSqlParameterSource params = new MapSqlParameterSource();
		// params.addValue("categoryId", categoryId);		
		Category category = (Category) jdbcTemplate.queryForObject(
			SELECT_CATEGORY_SQL,
			params,
			new RowMapper<Category>() {
				public Category mapRow(ResultSet rs, int rowNum)throws SQLException {
					Category category = new Category();
					category.setCategoryId(rs.getString("CATID"));
					category.setName(rs.getString("NAME"));
					category.setDescription(rs.getString("DESCN"));
					return category;
				}
			});
		return category;	 
	}

	private static final String SELECT_COUNT_SQL = "select count(*) from CATEGORY";
	public int getNumberOfCategories() {
		return jdbcTemplate.queryForObject(SELECT_COUNT_SQL, Collections.<String,Object> emptyMap(), Integer.class);
	}

 	private static final String INSERT_SQL = "insert into CATEGORY (CATID, NAME, DESCN) values (:catId, :name, :descn)";
	public void insertNewCategory(Category category) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("catId", category.getCategoryId());
		params.put("name", category.getName());
		params.put("descn", category.getDescription());
		jdbcTemplate.update(INSERT_SQL,	params);
		
		/*  // 위 방법 대신 아래와 같이 구현 가능
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("catId", category.getCategoryId());		
		params.addValue("name", category.getName());		
		params.addValue("descn", category.getDescription());				
		*/
		
		/*  // 위 방법 대신 아래와 같이 구현 가능
		String INSERT_SQL2 = "insert into CATEGORY (CATID, NAME, DESCN) values (:categoryId, :name, :description)";
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(category);
		jdbcTemplate.update(INSERT_SQL2, paramSource);
		*/
	}
}
