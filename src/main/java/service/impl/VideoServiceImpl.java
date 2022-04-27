package service.impl;

import java.util.List;

import DAO.VideoDAO;
import DAO.impl.VideoDAOImpl;
import model.Video;
import service.VideoService;

public class VideoServiceImpl implements VideoService{

	
	private VideoDAO videoDAO;
	
	public VideoServiceImpl() {
		videoDAO=new VideoDAOImpl();
	}

	@Override
	public Video findById(String id) {
		return videoDAO.findById(id);
	}

	@Override
	public Video findByHref(String href) {
		return videoDAO.findByHref(href);
	}

	@Override
	public List<Video> findAll() {
		return videoDAO.findAll();
	}

	@Override
	public List<Video> findAll(int pageNumber, int pageSize) {
		return videoDAO.findAll(pageNumber, pageSize);
	}

	@Override
	public Video create(Video entity) {
		return videoDAO.create(entity);
	}

	@Override
	public Video update(Video entity) {
		return videoDAO.update(entity);
	}

	@Override
	public Video delete(String href) {
		Video entity = videoDAO.findByHref(href);
		entity.setActive(false);
		return videoDAO.update(entity);
	}

}
