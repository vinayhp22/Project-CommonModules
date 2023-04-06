package com.xworkz.vinayhp.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xworkz.vinayhp.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CMSignInRepoImpl implements CMSignInRepo {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public UserEntity findByUserId(String userId) {
		log.info("findByUserId(String userId) " + userId);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("findByUserId");
			query.setParameter("byUserId", userId);
			UserEntity result = (UserEntity) query.getSingleResult();
			log.info("" + result);
			return result;
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		finally {
			entityManager.close();
		}
		return null;
	}

	@Override
	public boolean updateAttempts(String userId, int attempts) {
		log.info("updateAttempts(int attempts) " + attempts);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createNamedQuery("updateAttempts");
			query.setParameter("byAttempts", attempts);
			query.setParameter("byUserId", userId);
			int rowsAffected = query.executeUpdate();
			entityTransaction.commit();
			if (rowsAffected != 0) {
				log.info("Attempts updated : " + true);
				return true;
			}
			return false;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public boolean updateLock(String userId) {
		log.info("updateLock(String userId) "+userId);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Query query = entityManager.createNamedQuery("updateLock");
			query.setParameter("byUserId", userId);
			int rowsAffected = query.executeUpdate();
			entityTransaction.commit();
			if (rowsAffected != 0) {
				log.info("Locked : " + true);
				return true;
			}
			return false;
		} finally {
			entityManager.close();
		}
	}

}
