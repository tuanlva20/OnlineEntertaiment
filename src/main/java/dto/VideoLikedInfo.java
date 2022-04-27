package dto;

import java.util.Date;

public class VideoLikedInfo {
	private String videoId;
	private String title;
	private String href;
	private Integer totalLike;
	private Date newest;
	private Date oldest;

	public VideoLikedInfo() {
		super();
	}

	public VideoLikedInfo(String videoId, String title, String href, Integer totalLike, Date newest, Date oldest) {
		super();
		this.videoId = videoId;
		this.title = title;
		this.href = href;
		this.totalLike = totalLike;
		this.newest = newest;
		this.oldest = oldest;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Integer getTotalLike() {
		return totalLike;
	}

	public void setTotalLike(Integer totalLike) {
		this.totalLike = totalLike;
	}

	public Date getNewest() {
		return newest;
	}

	public void setNewest(Date newest) {
		this.newest = newest;
	}

	public Date getOldest() {
		return oldest;
	}

	public void setOldest(Date oldest) {
		this.oldest = oldest;
	}

    }
