package com.example.jpetstore.dao;

import java.util.Date;
import java.util.HashMap;

public interface EventDao {
  void insertNewEvent(HashMap<String, Date> hashMap);
  void closeEvent(Date curTime);
}
