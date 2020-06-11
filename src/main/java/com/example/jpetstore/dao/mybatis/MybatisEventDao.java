package com.example.jpetstore.dao.mybatis;

import java.util.Date;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.EventDao;
import com.example.jpetstore.dao.mybatis.mapper.EventMapper;

/**
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 */
@Repository
public class MybatisEventDao implements EventDao {	
	@Autowired
	private EventMapper accountMapper;
	
	public void insertNewEvent(HashMap<String, Date> map) {
		accountMapper.insertNewEvent(map);
	}

	public void closeEvent(Date curTime) {
		accountMapper.closeEvent(curTime);		
	}
}