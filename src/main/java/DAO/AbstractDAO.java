package DAO;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;

import org.hibernate.query.Query;

import utils.JpaUtil;

public class AbstractDAO<T> {
	public static final EntityManager entityManager = JpaUtil.getEntityManager();

	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	public T findById(Class<T> clazz, String id) {
		return entityManager.find(clazz, id);
	}

	public List<T> findAll(Class<T> clazz, boolean existIsActive) {
		String entity = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("select o from ").append(entity).append(" o ");
		if (existIsActive) {
			sql.append("where active=1");
		} else {
			sql.append("where active=0");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		return query.getResultList();
	}
	
	public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
		String entity = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("select o from ").append(entity).append(" o");
		if (existIsActive) {
			sql.append(" where active=1");
		} else {
			sql.append(" where active=0");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public List<T> findAllUser(Class<T> clazz, boolean idAdmin) {
		String entity = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("select o from ").append(entity).append(" o ");
		if (idAdmin) {             
			sql.append("where admin=1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		return query.getResultList();
	}
	
	public List<T> findAllUser(Class<T> clazz, boolean idAdmin, int pageNumber, int pageSize) {
		String entity = clazz.getSimpleName();
		StringBuilder sql = new StringBuilder();
		sql.append("select o from ").append(entity).append(" o");
		if (idAdmin) {
			sql.append(" where admin=1");
		}
		TypedQuery<T> query = entityManager.createQuery(sql.toString(), clazz);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}
	
	public T findOne(Class<T> clazz, String sql, Object... params) {
		TypedQuery<T> query=entityManager.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		List<T> result=query.getResultList();
		if (result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}
	
	public List<T> findMany(Class<T> clazz, String sql, Object... params) {
		TypedQuery<T> query=entityManager.createQuery(sql, clazz);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findManyByNativeQuery(String sql, Object... params) {
		@SuppressWarnings("rawtypes")
		Query query=(Query) entityManager.createNativeQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (List<Object[]>) query.getResultList();	
	}
	
	public T insert(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			System.out.println("Insert succeed");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot insert entity: "+entity.getClass().getSimpleName()+" to DB");
			throw new RuntimeException(e);
		}
	}

	public T update(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(entity);
			entityManager.getTransaction().commit();
			System.out.println("Update succeed");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot update entity: "+entity.getClass().getSimpleName()+" to DB");
			throw new RuntimeException(e);
		}
	}

	public T delete(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(entity);
			entityManager.getTransaction().commit();
			System.out.println("Delete succeed");
			return entity;
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			System.out.println("Cannot delete entity: "+entity.getClass().getSimpleName());
			throw new RuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<T> callStored(String nameStored, Map<String, Object> params){
		   StoredProcedureQuery query=entityManager.createNamedStoredProcedureQuery(nameStored);
		   params.forEach((key, value) -> query.setParameter(key, value));
		   return (List<T>)query.getResultList();
	} 
}
