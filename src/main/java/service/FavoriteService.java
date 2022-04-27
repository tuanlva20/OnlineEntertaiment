package service;

import java.util.List;

import model.Favorite;
import model.Users;
import model.Video;

public interface FavoriteService {
	List<Favorite> findByUser(String userName);
	Favorite findByUserIdAndVideoId(String userId, String videoId);
	Favorite insert(Users user, Video video);
}
