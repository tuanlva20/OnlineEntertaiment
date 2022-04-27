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
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/admin/user/view", "/admin/user/create", "/admin/user/update", "/admin/user/delete",
		"/admin/user/*" })
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();
	Users user;
	List<Users> users = new ArrayList<Users>();
	public UserController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		users = userService.findAll();
		HttpSession session = request.getSession();
		Users isLogin = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		if (isLogin != null && isLogin.getAdmin() == Boolean.TRUE) {
			String uri = request.getRequestURI();
			if (uri.contains("view")) {
				this.doGetView(request, response);
			} else if (uri.contains("delete")) {
				this.doGetDelete(request, response);
			} else if (uri.contains("edit")) {
				this.doGetEdit(request, response);
			}
		} else {
			response.sendRedirect("/ASSM-Nhom/client/home");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users currentUsers = (Users) session.getAttribute(SessionAttr.CURRENT_USER);
		users = userService.findAll();
		request.setAttribute("users", users);
		if (currentUsers != null && currentUsers.getAdmin() == Boolean.TRUE) {
			String uri = request.getRequestURI();
			if (uri.contains("create")) {
				this.doPostCreate(request, response);
			} else if (uri.contains("update")) {
				this.doPostUpdate(request, response);
			} else if (uri.contains("delete")) {
				this.doPostDelete(request, response);
			} else if (uri.contains("new")) {
				this.doPostNew(request, response);
			}
		} else {
			response.sendRedirect("/ASSM-Nhom/client/home");
		}
	}
	
	// doGetEdit
	private void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.statusButton(request, response);
		String uname=request.getParameter("uname");
		String index=request.getParameter("index");
		user = new Users();
		user = userService.findByUserName(uname);
		request.setAttribute("users", users);
		request.setAttribute("user", user);
		request.setAttribute("index", index);
		request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
	}

	// doGetDelete
	private void doGetDelete(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.statusButton(request, response);
			response.setContentType("application/json");
			String uname = request.getParameter("uname");
			Users userDeleted = userService.delete(uname);
			if (userDeleted != null) {
				response.setStatus(204);
			} else {
				response.setStatus(400);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// doGetView
	private void doGetView(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.statusButton(request, response);
			request.setAttribute("users", users);
			request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// /ASSM-Nhom/admin/user/new
	private void doPostNew(HttpServletRequest request, HttpServletResponse response) {
		try {
			this.statusButton(request, response);
			String method = request.getMethod();
			if (method.equalsIgnoreCase("post")) {
				user = new Users();
				user.setAdmin(false);
				users = userService.findAll();
				request.setAttribute("users", users);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
			}
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

	// /ASSM-Nhom/admin/user/delete
	private void doPostDelete(HttpServletRequest request, HttpServletResponse response) {
		this.statusButton(request, response);
		try {
			BeanUtils.populate(user, request.getParameterMap());
			Users userDeleted=userService.delete(user.getUsername());
			if (userDeleted!=null) {
				response.sendRedirect("/ASSM-Nhom/admin/user/view");
				response.setStatus(204);
			}else {
				request.setAttribute("message", "Xóa không thành công. Vui lòng thử lại");
				request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
				response.setStatus(400);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// /ASSM-Nhom/admin/user/update
	private void doPostUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		this.statusButton(request, response);
		Users userUpdate=new Users();
		try {
			BeanUtils.populate(userUpdate, request.getParameterMap());
			if (checkNull(userUpdate)) {
				Users userUpdated=userService.update(userUpdate);
				if (userUpdated!=null) {
					response.sendRedirect("/ASSM-Nhom/admin/user/view");
					response.setStatus(204);
				}else {
					request.setAttribute("message", "Cập nhật không thành công. Vui lòng thử lại");
					request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
					response.setStatus(400);
				}
			}else {
				request.setAttribute("message", "Không để trống dữ liệu");
				request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	// /ASSM-Nhom/admin/user/create
	private void doPostCreate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.statusButton(request, response);
		user=new Users();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			Users unameExisted=userService.findByUserName(user.getUsername());
			Users gmailExisted=userService.findByEmail(user.getEmail());
			if (checkNull(user)) {
				if (unameExisted!=null) {
					request.setAttribute("message", "Username đã tồn tại");
					request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
				}else if(gmailExisted!=null) {
					request.setAttribute("message", "Gmail đã tồn tại");
					request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
				}else {
					Users userInserted=userService.create(user.getUsername(),user.getPassword(), user.getFullname(), user.getEmail(), user.getAdmin());
					if (userInserted!=null) {
						response.sendRedirect("/ASSM-Nhom/admin/user/view");
						response.setStatus(204);
					}else response.setStatus(400);
				}
			}else {
				request.setAttribute("message", "Không để trống dữ liệu");
				request.getRequestDispatcher("/jsp/admin/UserAdmin.jsp").forward(request, response);
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			System.out.println("Error Users Data input");
			e.printStackTrace();
		}
	}
	
	private void statusButton(HttpServletRequest request, HttpServletResponse response) {
		try {
			int index=Integer.parseInt(request.getParameter("index"));
			if (index>=0) {
				request.setAttribute("button", true);
			}
		} catch (Exception e) {
			request.setAttribute("button", false);
			System.out.println(e.getMessage());
		}
	}
	
	private boolean checkNull(Users user) {
			if (user.getUsername()!=""&&user.getPassword()!=""&&user.getFullname()!=""&&user.getEmail()!="") {
				return true;
			}else{
				return false;
			}
	}
}
