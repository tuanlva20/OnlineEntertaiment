package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import DAO.UserDAO;
import DAO.impl.UserDAOImpl;
import constant.NameParams;
import model.Users;
import service.UserService;

public class UserServiceImpl implements UserService{
	
	private UserDAO dao;
	
	public UserServiceImpl() {
		dao=new UserDAOImpl();
	}
	@Override
	public Users findById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}
	@Override
	public Users findByEmail(String email) {
		return dao.findByEmail(email);
	}
	@Override
	public Users findByUserName(String username) {
		return dao.findByUserName(username);
	}
	@Override
	public Users login(String username, String password) {
		// TODO Auto-generated method stub
		return dao.findByUsernameAndPassword(username, password);
	}
	@Override
	public Users resetPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Users> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public List<Users> findAll(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return dao.findAll(pageNumber, pageSize);
	}
	@Override
	public Users create(String username, String password, String fullnane, String email, boolean isAdmin) {
		Users newUser=new Users();
		newUser.setUsername(username);
		newUser.setPassword(password); //bcrypt md5
		newUser.setEmail(email);
		newUser.setFullname(fullnane);
		newUser.setAdmin(isAdmin);
		return dao.create(newUser);
	}
	@Override	
	public Users update(Users entity) {
		return dao.update(entity);
	}
	@Override
	public Users delete(String username) {
		Users users=dao.findByUserName(username);
		return dao.delete(users);
	}
	@Override
	public List<Users> findUsersLikedVideoByVideoHref(String href) {
		Map<String, Object> params=new HashMap<>();
		params.put(NameParams.Video_Href, href);
		return dao.findVideoLikedByHrefUsers(params);
	}
}
