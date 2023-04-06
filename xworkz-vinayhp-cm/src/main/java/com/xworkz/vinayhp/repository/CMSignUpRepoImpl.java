package com.xworkz.vinayhp.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xworkz.vinayhp.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class CMSignUpRepoImpl implements CMSignUpRepo {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean save(UserEntity entity) {
		log.info("save in CMSignUpRepoImpl"+entity);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entity);
		entityTransaction.commit();
		}finally {
			entityManager.close();
		}
		return true;
	}
	
	@Override
	public Long countByUserId(String userId) {
		log.info("countByUserId(String userId) in repo "+userId);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("countByUserId");
			query.setParameter("byUserId", userId);
			Long result = (Long) query.getSingleResult();
			return result;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public Long countByEmail(String email) {
		log.info("countByEmail(String email) in repo "+email);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("countByEmail");
			query.setParameter("byEmail", email);
			Long result = (Long) query.getSingleResult();
			return result;
		} finally {
			entityManager.close();
		}
	}
	
	@Override
	public Long countByMobile(Long mobile) {
		log.info("countByMobile(Long mobile) in repo "+mobile);
		EntityManager entityManager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = entityManager.createNamedQuery("countByMobile");
			query.setParameter("byMobile", mobile);
			Long result = (Long) query.getSingleResult();
			return result;
		} finally {
			entityManager.close();
		}
	}

}
