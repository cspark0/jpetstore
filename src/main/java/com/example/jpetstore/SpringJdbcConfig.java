package com.example.jpetstore;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

import com.example.jpetstore.dao.jdbc.JdbcDaoSupportCategoryDao;

@Configuration
public class SpringJdbcConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	JdbcDaoSupportCategoryDao jdbcDaoSupportCategoryDao() {
		JdbcDaoSupportCategoryDao dao = new JdbcDaoSupportCategoryDao();
		dao.setDataSource(dataSource);
		return dao;
	}
}
