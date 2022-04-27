package controller.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import model.Favorite;
import model.Users;
import model.Video;
import service.FavoriteService;
import service.impl.FavoriteServiceImpl;

@WebServlet(urlPatterns = { "/client/favorite", "/client/unlike", "/client/share" })
public class ClientFavorite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FavoriteService favoriteService = new FavoriteServiceImpl();

	public ClientFavorite() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		this.doGetFavorite(session, request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println(path);
		switch (path) {
		case "unlike":
			request.getRequestDispatcher("/jsp/client/Favorite.jsp").forward(request, response);
			break;
		case "share":
			request.getRequestDispatcher("/jsp/client/share.jsp").forward(request, response);
			break;
		default:
			break;
		}
	}

//	private void doPostUnlike(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		request.getRequestDispatcher("/jsp/client/Favorite.jsp").forward(request, response);
//	}

	private void doGetFavorite(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Users users = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		List<Favorite> favorites = favoriteService.findByUser(users.getUsername());
		List<Video> videos = new ArrayList<Video>();
		favorites.forEach(item -> videos.add(item.getVideo()));
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/jsp/client/Favorite-Video.jsp").forward(request, response);
	}

}
