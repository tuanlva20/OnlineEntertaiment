package DAO;

import java.util.List;

import model.Video;

public interface VideoDAO {
	Video findById(String id);
	Video findByHref(String href);
	List<Video> findAll();
	List<Video> findAll(int pageNumber, int pageSize);
	Video create(Video entity);
	Video update(Video entity);
	Video delete(Video entity);
}
