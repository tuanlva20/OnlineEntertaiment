package DAO.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.AbstractDAO;
import DAO.StatsDAO;
import dto.VideoLikedInfo;

public class StatsDAOImpl extends AbstractDAO<Object[]> implements StatsDAO{

	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		String sql="SELECT v.id, v.title, v.href, COUNT(f.VideoId) as totallike, MAX(f.likedate) AS newest, MIN(f.likedate) AS oldest"
				+ " FROM Video v LEFT JOIN Favorite f ON v.id=f.videoid"
				+ " WHERE v.active=1"
				+ " GROUP BY v.id, v.title, v.href"
				+ " ORDER BY COUNT(f.VideoId) DESC";
		List<Object[]> objects=super.findManyByNativeQuery(sql);
		List<VideoLikedInfo> videoLikedInfos=new ArrayList<VideoLikedInfo>();
		objects.forEach(object ->{
			VideoLikedInfo videoInfo=setDataVideoLikedInfo(object);
			videoLikedInfos.add(videoInfo);
		});
		return videoLikedInfos;
	}

	private VideoLikedInfo setDataVideoLikedInfo (Object[] object) {
		VideoLikedInfo videoInfo=new VideoLikedInfo();
		videoInfo.setVideoId((String)object[0]);
		videoInfo.setTitle((String)object[1]);
		videoInfo.setHref((String)object[2]);
		videoInfo.setTotalLike(object[3] == null ? 0 : (Integer)object[3]);
		videoInfo.setNewest((Date)object[4]);
		videoInfo.setOldest((Date)object[5]);
		return videoInfo;
	}
}
