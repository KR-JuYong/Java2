package project;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private DAO dao;
	private ServletContext ctx;
		
		final String START_PAGE="/Selenium.jsp";
		
		public void init(ServletConfig config) throws ServletException{
			super.init(config);
			dao=new DAO();
			ctx=getServletContext();
		}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	String action = request.getParameter("action");
	dao=new DAO();
	
	Method m;
	String view = null;
	
	if(action == null) {
			action="listSelenium";
	}
			try {
				m=this.getClass().getMethod(action, HttpServletRequest.class);
				view= (String)m.invoke(this, request);
			}catch(NoSuchMethodException e) {
				e.printStackTrace();
				ctx.log("요청 action 없음!!");
				request.setAttribute("error", "action 파라미터가 잘못되었습니다!");
				view= START_PAGE;
			}catch( Exception e) {
				e.printStackTrace();
			}
			if(view.startsWith("redirect:/")) {
				String rview= view.substring("redirect:/".length());
				response.sendRedirect(rview);
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);
			}
		}
	

	public String listSelenium(HttpServletRequest request) {
		List<Selenium2> list2;
		List<Selenium3> list3;
		List<Selenium4> list4;
		try {
			list2= dao.getAll2();
			request.setAttribute("Selenium", list2);
			list3=dao.getAll3();
			request.setAttribute("Selenium", list3);
			list4=dao.getAll4();
			request.setAttribute("Selenium", list4);
		}catch(Exception e) {
			e.printStackTrace();
			ctx.log("증권 목록 생성 과정에서 문제 발생");
			request.setAttribute("error", "증권 목록이 정상적으로 처리되지 않았습니다.!");
		}
		return "/Selenium.jsp";
	}
	
	public String getSelenium(HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter("id"));
		try {
			Selenium2 s2 = dao.getSelenium2(id);
			request.setAttribute("Selenium2", s2);
			Selenium3 s3 = dao.getSelenium3(id);
			request.setAttribute("Selenium3", s3);
			Selenium4 s4 = dao.getSelenium4(id);
			request.setAttribute("Selenium4", s4);
		}catch(SQLException e) {
			e.printStackTrace();
			ctx.log("증권을 가져오는 과정에서 문제 발생!");
			request.setAttribute("error", "증권을 정상적으로 가져오지 못했습니다!");
		}
		return "/Selenium.jsp";
	}
}
