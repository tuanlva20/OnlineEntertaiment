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

@WebServlet("/client/detailll")
public class ClientDetaill extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public ClientDetaill() {
        super();
    }
    
    private VideoService videoService=new VideoServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String href=request.getParameter("id");
		List<Video> videos=videoService.findAll();
		request.setAttribute("videos", videos);
		Video video=videoService.findByHref(href);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/jsp/client/DetailVideoo.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
