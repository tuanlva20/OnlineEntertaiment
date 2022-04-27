package controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Video;
import service.VideoService;
import service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/client/home","/client/index"})
public class ClientHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final int VIDEO_MAX_PAGE_SIZE=6;
    public ClientHome() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		VideoService videoService=new VideoServiceImpl();
		List<Video> countVideos=videoService.findAll();
		int maxPage=(int) Math.ceil(countVideos.size()/(double)VIDEO_MAX_PAGE_SIZE);
		request.setAttribute("maxPage", maxPage);
		String pageNumber=request.getParameter("page");
		
		List<Video> videos;
		
		if (pageNumber==null || Integer.valueOf(pageNumber) > maxPage) {
			videos=videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currentPage", 1);
		}else {
			videos=videoService.findAll(Integer.parseInt(pageNumber), VIDEO_MAX_PAGE_SIZE);
			request.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/jsp/client/ClientHome.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
