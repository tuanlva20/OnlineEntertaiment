package service.impl;

import java.util.List;

import DAO.FavoriteDAO;
import DAO.impl.FavoriteDAOImpl;
import model.Favorite;
import model.Users;
import model.Video;
import service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

	private FavoriteDAO dao;

	public FavoriteServiceImpl() {
		dao = new FavoriteDAOImpl();
	}

	@Override
	public List<Favorite> findByUser(String userName) {
		return dao.findByUser(userName);
	}

	@Override
	public Favorite findByUserIdAndVideoId(String userId, String videoId) {
		return dao.findByUserIdAndVideoId(userId, videoId);
	}

	@SuppressWarnings("null")
	@Override
	public Favorite insert(Users user, Video video) {
		Favorite entity=dao.findByUserIdAndVideoId(user.getUsername(), video.getId());
		if (entity==null) {
			entity.setUser(user);
			entity.setVideo(video);
			return dao.insert(entity);
		}
		return null;
	}

}
