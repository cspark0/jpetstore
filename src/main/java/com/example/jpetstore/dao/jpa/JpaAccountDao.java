package com.example.jpetstore.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.jpetstore.dao.AccountDao;
import com.example.jpetstore.domain.Account;

/**
 * @author Changsup Park
 */
@Repository
public class JpaAccountDao implements AccountDao {

	@PersistenceContext
    private EntityManager em;

    public Account getAccount(String username) throws DataAccessException {
        return em.find(Account.class, username);         
	}

	public Account getAccount(String username, String password) 
			throws DataAccessException {
		TypedQuery<Account> query = em.createQuery(
                "select a from Account a " +
                "where a.username=:id and a.password=:pw",
                Account.class);
        query.setParameter("id", username);
        query.setParameter("pw", password);
        Account account = null;
        try {
        	account = query.getSingleResult();
        } catch(NoResultException ex) {
        	return null;
        }
        return account;		
	}

	public void insertAccount(Account account) throws DataAccessException {
        em.persist(account);
    }

	public void updateAccount(Account account) throws DataAccessException {
        em.merge(account);
    }
	
	public List<String> getUsernameList() throws DataAccessException {
		TypedQuery<String> query = em.createQuery(
                "select a.username from Account a", String.class);
        return query.getResultList();
    }
	
	/*
	public void deleteAccount(Account account) {
		Account managedAccount = em.merge(account);
        em.remove(managedAccount);
    }
	*/
}