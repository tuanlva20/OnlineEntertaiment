package DAO.impl;

import java.util.List;

import DAO.AbstractDAO;
import DAO.FavoriteDAO;
import model.Favorite;

public class FavoriteDAOImpl extends AbstractDAO<Favorite> implements FavoriteDAO{

	public List<Favorite> findByUser(String userName) {
		String sql="select o from Favorite o where o.user.id=?0";
		return super.findMany(Favorite.class, sql, userName);
	}

	public Favorite findByUserIdAndVideoId(String userId, String videoId) {
		String sql="select o from Favorite o where o.user.id=?0 and o.video.id=?0";
		return super.findOne(Favorite.class, sql, userId, videoId);
	}

}
