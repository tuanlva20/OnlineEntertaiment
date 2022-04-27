package service;

import java.util.List;

import model.Users;

public interface UserService {
	Users findById(String id);
	Users findByEmail(String email);
	Users findByUserName(String username);
	Users login(String username, String password);
	Users resetPassword(String email);
	List<Users> findAll();
	List<Users> findAll(int pageNumber, int pageSize);
	Users create(String username, String password, String fullname,String email, boolean isAdmin);
	Users update(Users entity);
	Users delete(String username);
	List<Users> findUsersLikedVideoByVideoHref(String href);
}
