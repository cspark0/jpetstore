package com.example.jpetstore.dao.mybatis.mapper;

import java.util.Date;
import java.util.HashMap;

public interface EventMapper {
  void insertNewEvent(HashMap<String, Date> map);
  void closeEvent(Date curTime);
}
