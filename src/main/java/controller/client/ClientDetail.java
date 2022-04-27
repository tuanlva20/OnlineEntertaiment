package controller.client;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Video;
import service.VideoService;
import service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = {"/client/detail"})
public class ClientDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ClientDetail() {
        super();
    }

    private VideoService videoService=new VideoServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionParam=request.getParameter("action");
		String href=request.getParameter("id");
		HttpSession session=request.getSession();
		List<Video> videos=videoService.findAll();
		request.setAttribute("videos", videos);
		switch (actionParam) {
		case "watch":
			this.doGetWatch(session, href, request, response);
			break;
		default:
			break;
		}
		
	}

	private void doGetWatch(HttpSession session, String href, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Video video=videoService.findByHref(href);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/jsp/client/Detail-Video.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
