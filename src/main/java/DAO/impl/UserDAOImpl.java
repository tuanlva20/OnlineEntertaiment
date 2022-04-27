package DAO.impl;

import java.util.List;
import java.util.Map;

import DAO.AbstractDAO;
import DAO.UserDAO;
import constant.NameStored;
import model.Users;

public class UserDAOImpl extends AbstractDAO<Users> implements UserDAO{

	@Override
	public Users findById(String id) {
		return super.findById(Users.class, id);
	}

	@Override
	public Users findByEmail(String email) {
		String sql="select o from Users o where o.email=?0";
		return super.findOne(Users.class, sql, email);
	}

	@Override
	public Users findByUserName(String username) {
		String sql="select o from Users o where o.username=?0";
		return super.findOne(Users.class, sql, username);
	}

	@Override
	public Users findByUsernameAndPassword(String username, String password) {
		String sql="select o from Users o where o.username=?0 and o.password=?1";
		return super.findOne(Users.class, sql, username, password);
	}

	@Override
	public List<Users> findAll() {
		return super.findAllUser(Users.class, false);
	}

	@Override
	public List<Users> findAll(int pageNumber, int pageSize) {
		return super.findAllUser(Users.class, false, pageNumber, pageSize);
	}

	@Override
	public Users create(Users entity) {
		return super.insert(entity);
	}

	@Override
	public Users delete(Users entity) {
		return super.delete(entity);
	}

	@Override
	public Users update(Users entity) {
		return super.update(entity);
	}

	@Override
	public List<Users> findVideoLikedByHrefUsers(Map<String, Object> params) {
		return super.callStored(NameStored.FIND_USERS_LIKED_VIDEO_BY_VIDEOHREF, params);
	}

}
