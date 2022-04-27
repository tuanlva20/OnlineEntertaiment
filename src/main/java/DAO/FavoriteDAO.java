package DAO;

import java.util.List;

import model.Favorite;

public interface FavoriteDAO {
	List<Favorite> findByUser(String userName);
	Favorite findByUserIdAndVideoId(String userId, String videoId);
	Favorite insert(Favorite entity);
	Favorite update(Favorite entity);
}
