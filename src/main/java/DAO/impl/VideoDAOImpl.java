package DAO.impl;

import java.util.List;

import DAO.AbstractDAO;
import DAO.VideoDAO;
import model.Video;

public class VideoDAOImpl extends AbstractDAO<Video> implements VideoDAO{

	@Override
	public Video findById(String id) {
		return super.findById(Video.class, id);
	}

	@Override
	public Video findByHref(String href) {
		String sql="select o from Video o where o.href=?0";
		return super.findOne(Video.class, sql, href);
	}

	@Override
	public List<Video> findAll() {
		return super.findAll(Video.class, true);
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return super.findAll(Video.class, true, pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		return super.insert(entity);
	}
}
