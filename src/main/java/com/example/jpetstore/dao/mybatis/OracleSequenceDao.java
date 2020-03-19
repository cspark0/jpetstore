package com.example.jpetstore.dao.mybatis;

import org.springframework.dao.DataAccessException;
import com.example.jpetstore.domain.Sequence;

public class OracleSequenceDao extends MybatisSequenceDao {

  /**
   * Get the next sequence using an Oracle thread-safe sequence
   * @param name Name is the name of the oracle sequence.
   * @return the next sequence
   */
  public int getNextId(String name) throws DataAccessException {
    Sequence sequence = new Sequence();
    sequence.setName(name);
    sequence = sequenceMapper.getOracleSequence(sequence);
    return sequence.getNextId();
  }
}
