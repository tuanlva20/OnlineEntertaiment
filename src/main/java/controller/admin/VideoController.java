package controller.admin;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import constant.SessionAttr;
import model.Users;
import model.Video;
import service.VideoService;
import service.impl.VideoServiceImpl;

@WebServlet(urlPatterns = { "/admin/video", "/admin/video/create", "/admin/video/update", "/admin/video/delete",
		"/admin/new" })
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VideoService videoService = new VideoServiceImpl();
	List<Video> videos = new ArrayList<Video>();

	public VideoController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users currentUser = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUser != null && currentUser.getAdmin() == Boolean.TRUE) {
			String actionString = request.getParameter("action");
			switch (actionString) {
			case "view":
				this.doGetOverView(request, response);
				break;
			case "delete":
				this.doGetDelete(request, response);
				break;
			case "edit":
				this.doGetEdit(request, response);
				break;
			default:
				break;
			}
		} else {
			response.sendRedirect("/ASSM-Nhom/client/home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		Users currentUsers=(Users) session.getAttribute(SessionAttr.CURRENT_USER);
		if (currentUsers!=null&&currentUsers.getAdmin()==Boolean.TRUE) {
			String action=request.getParameter("action");
			switch (action) {
			case "new":
				this.doPostNew(request, response);
				break;
			case "create":
				this.doPostCreate(request, response);
				break;
			case "delete":
				this.doPostDelete(request, response);
				break;
			case "update":
				this.doPostUpdate(request, response);
				break;
			default:
				response.sendRedirect("/ASSM-Nhom/client/home");
				break;
			}
		}else {
			response.sendRedirect("/ASSM-Nhom/client/home");
		}
	}

	// /ASSM-Nhom/admin/video?action=view
	private void doGetOverView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		videos = videoService.findAll();
		System.out.println(videos.size());
		request.setAttribute("videos", videos);
		request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);

	}

	// /ASSM-Nhom/admin/video?action=delete&href={href}
	private void doGetDelete(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
		String href = request.getParameter("href");
		Video videoDeleted = videoService.delete(href);
		if (videoDeleted != null) {
			response.setStatus(204);
		} else
			response.setStatus(400);
	}

	// /ASSM-Nhom/admin/video?action=add

	// /ASSM-Nhom/admin/video?action=edit&href={href}
	private void doGetEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String href = request.getParameter("href");
		Video video = videoService.findByHref(href);
		request.setAttribute("videos", videos);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);
		response.setStatus(204);

	}
	
	//doPostNew
	private void doPostNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video=new Video();
		video.setActive(true);
		request.setAttribute("video", video);
		request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);
	}

	//doPostDelete
	private void doPostDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String href=request.getParameter("href");
			if (href!="") {
				Video videoDeleted=videoService.delete(href);
				if (videoDeleted!=null) {
					request.setAttribute("message", "Xóa video thành công");
					request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("message", "Xóa video thất bại");
				request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);
			}
	}

	//doPostUpdate
	private void doPostUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video video=new Video();
		try {
			BeanUtils.populate(video, request.getParameterMap());
			if (checkNull(video)) {
				Video videoUpdated=videoService.update(video);
				if (videoUpdated!=null) {
					response.sendRedirect("/ASSM-Nhom/admin/video?action=view");
				}
			}else {
				request.setAttribute("message", "Không để trống thông tin");
				request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}

	//doPostCreate
	private void doPostCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Video videoCreate=new Video();
		try {
			BeanUtils.populate(videoCreate, request.getParameterMap());
			if (checkNull(videoCreate)) {
				Video videoHref= videoService.findByHref(videoCreate.getHref());
				Video videoId= videoService.findById(videoCreate.getId());
				if (videoHref==null&&videoId==null) {
					Video videoCreated=videoService.create(videoCreate);
					if (videoCreated!=null) {
						response.sendRedirect("/ASSM-Nhom/admin/video?action=view");
					}
				}else {
					request.setAttribute("message", "ID hoặc Href đã tồn tại. Vui lòng thử lại");
					request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("message", "Không để trống thông tin");
				request.getRequestDispatcher("/jsp/admin/VideoAdmin.jsp").forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	//CheckNull
	private boolean checkNull(Video video) {
		if (video.getId()!=""&&video.getTitle()!=""&&video.getHref()!=""&&video.getDescription()!="") {
			return true;
		}else {
			return false;
		}
	}
}
