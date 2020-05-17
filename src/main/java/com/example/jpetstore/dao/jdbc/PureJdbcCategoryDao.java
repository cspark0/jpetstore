package com.example.jpetstore.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;
import com.example.jpetstore.dao.CategoryDao;
import com.example.jpetstore.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public class PureJdbcCategoryDao implements CategoryDao {
  
	private DataSource dataSource;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private SQLExceptionTranslator exceptionTranslator = new SQLStateSQLExceptionTranslator();
	
	private static final String SELECT_CATEGORY_LIST_SQL = "select CATID, NAME, DESCN from CATEGORY";
	private static final String SELECT_CATEGORY_SQL = "select CATID, NAME || ' (by PureJDBC)' as NAME, DESCN "
													+ "from CATEGORY where CATID = ?";
	
	public List<Category> getCategoryList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_CATEGORY_LIST_SQL);
			if (rs.next()) {
				List<Category> list = new ArrayList<Category>();
				do {
					Category category = new Category();
					category.setCategoryId(rs.getString("CATID"));
					category.setName(rs.getString("NAME"));
					category.setDescription(rs.getString("DESCN"));
					list.add(category);
				} while (rs.next());
				return list;
			} else {
				return Collections.emptyList();
			}
		} catch (SQLException e) {
			throw exceptionTranslator.translate("getCategoryList", SELECT_CATEGORY_LIST_SQL, e);
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(stmt);
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
	
	public Category getCategory(String categoryId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			pstmt = conn.prepareStatement(SELECT_CATEGORY_SQL);
			pstmt.setString(1, categoryId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getString("CATID"));
				category.setName(rs.getString("NAME"));
				category.setDescription(rs.getString("DESCN"));
				return category;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw exceptionTranslator.translate("getCategory", SELECT_CATEGORY_SQL, e);
		} finally {
			JdbcUtils.closeResultSet(rs);
			JdbcUtils.closeStatement(pstmt);
			DataSourceUtils.releaseConnection(conn, dataSource);
		}	
	}

	private static final String INSERT_SQL = "insert into CATEGORY (CATID, NAME, DESCN) values (?, ?, ?)";
	public void insertNewCategory(Category category) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			pstmt = conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, category.getCategoryId());
			pstmt.setString(2, category.getName());
			pstmt.setString(2, category.getDescription());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw exceptionTranslator.translate("insertNewCategory", INSERT_SQL, e);
		} finally {
			JdbcUtils.closeStatement(pstmt);
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
}

