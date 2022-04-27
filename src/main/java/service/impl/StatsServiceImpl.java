package service.impl;

import java.util.List;

import DAO.StatsDAO;
import DAO.impl.StatsDAOImpl;
import dto.VideoLikedInfo;
import service.StatsService;

public class StatsServiceImpl implements StatsService{
	StatsDAO statsDAO;
	
	public StatsServiceImpl(){
		statsDAO=new StatsDAOImpl();
	}
	
	@Override
	public List<VideoLikedInfo> findVideoLikedInfo() {
		return statsDAO.findVideoLikedInfo();
	}
}
