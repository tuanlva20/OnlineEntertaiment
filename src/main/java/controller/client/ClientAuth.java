package controller.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import constant.SessionAttr;
import model.Users;
import service.UserService;
import service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/client/changepassword", "/client/forgotpass", "/client/login", "/client/register","/client/logout" })
public class ClientAuth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService=new UserServiceImpl();
	public ClientAuth() {
		super();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String path=request.getServletPath();
		switch (path) {
		case "/client/changepassword":
			this.doChangePassword(request, response);
			break;
		case "/client/forgotpass":
			this.doForgotPass(request, response);
			break;
		case "/client/login":
			this.doLogin(request, response);
			break;
		case "/client/register":
			this.doRegister(request, response);
			break;	
		case "/client/logout":
			this.doLogout(session,request, response);
			break;
		default:
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		String path=request.getServletPath();
		switch (path) {
		case "/client/login":
			this.doPostLogin(session,request, response);
			break;
		case "/client/register":
			this.doPostRegister(session,request, response);
			break;
		default:
			break;
		}
		
	}
	
	//Hàm Get
	private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/client/Register.jsp").forward(request, response);
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/client/Login.jsp").forward(request, response);
	}

	private void doForgotPass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/jsp/client/ForgotPass.jsp").forward(request, response);
	}

	private void doChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/jsp/client/ChangePassword.jsp").forward(request, response);
	}
	
	private void doLogout(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		session.removeAttribute(SessionAttr.CURRENT_USER);
		response.sendRedirect("home");
	}
	
	//Hàm Post
	private void doPostLogin(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		Users user=userService.login(username, password);
		if (user!=null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			if (user.getAdmin()==Boolean.TRUE) {
				response.sendRedirect("/ASSM-Nhom/admin");
			}else {
				response.sendRedirect("home");
			}
		}else {
			request.setAttribute("message", "Username hoặc Password không hợp lệ!!");
			request.getRequestDispatcher("/jsp/client/Login.jsp").forward(request, response);
		}
	}
	
	private void doPostRegister(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		String fullname=request.getParameter("fullname");
		Users user=userService.create(username, password, fullname, email, false);
		if (user!=null) {
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			response.sendRedirect("home");
		}else {
			response.sendRedirect("register");
		}
	}
}
