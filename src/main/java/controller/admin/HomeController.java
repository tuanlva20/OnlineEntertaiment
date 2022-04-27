package controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import dto.VideoLikedInfo;
import model.Users;
import service.StatsService;
import service.UserService;
import service.impl.StatsServiceImpl;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/admin","/admin/favorites"})
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	private StatsService statsService=new StatsServiceImpl();
	private UserService userService=new UserServiceImpl();
    public HomeController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		Users currentUser=(Users)session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null && currentUser.getAdmin() ==Boolean.TRUE) {
			String path=request.getServletPath();
			switch (path) {
			case "/admin":
				this.doGetHome(request, response);
				break;
			case "/admin/favorites":
				this.doGetFavorites(request, response);
				break;
			}
		}else {
			response.sendRedirect("client/home");
		}
		
	}

	//localhost:8080/ASSM-Nhom/admin/favorites?href={videoHref}
	private void doGetFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out=response.getWriter();
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("application/json");
		String videoHref=request.getParameter("href");
		List<Users> users=userService.findUsersLikedVideoByVideoHref(videoHref);
		List<VideoLikedInfo> videos=statsService.findVideoLikedInfo();
		request.setAttribute("users", users);
		request.setAttribute("videos", videos);
		request.setAttribute("videoHref", videoHref);
		request.getRequestDispatcher("/jsp/admin/HomeAdmin.jsp").forward(request, response);
//		if (users.isEmpty()) {
//			response.setStatus(400);
//		} else {
//			ObjectMapper mapper=new ObjectMapper();
//			String dataRespone=mapper.writeValueAsString(users);
//			response.setStatus(200);
////			out.print(dataRespone);
////			out.flush();
//		}
		
	}

	private void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<VideoLikedInfo> videos= statsService.findVideoLikedInfo();
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/jsp/admin/HomeAdmin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
