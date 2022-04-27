package DAO;

import java.util.List;
import java.util.Map;

import model.Users;

public interface UserDAO {
	Users findById(String id);
	Users findByEmail(String email);
	Users findByUserName(String username);
	Users findByUsernameAndPassword(String username, String password);
	List<Users> findAll();
	List<Users> findAll(int pageNumber, int pageSize);
	Users create(Users entity);
	Users update(Users entity);
	Users delete(Users entity);
	List<Users> findVideoLikedByHrefUsers(Map<String, Object> params);
}
