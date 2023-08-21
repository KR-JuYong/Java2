package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@WebServlet(urlPatterns = "/cook")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "c:/Temp/img")
public class CookController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CookDAO dao;
	private ServletContext ctx;
	private Recipe rc;

	private final String START_PAGE = "project/cookList.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new CookDAO();
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		dao = new CookDAO();

		Method m;
		String view = null;

		if (action == null) {
			action = "listCook";
		}
		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			view = (String) m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			request.setAttribute("error", "action 파라미터가 잘못되었습니다!");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	}

	public String addCook(HttpServletRequest request) {
		Cook c = new Cook();
		try {
			Part part = request.getPart("file");
			String fileName = getFilename(part);
			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName);
			}
			BeanUtils.populate(c, request.getParameterMap());
			c.setImg("/img/" + fileName);
			dao.addCook(c);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("레시피 추가 과정에서 문제 발생");
			request.setAttribute("error", "레시피가 정상적으로 등록되지 않았습니다.");
			return listCook(request);
		}
		return "redirect:/cook?action=listCook";
	}

	public String deleteCook(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		try {
			dao.delCook(aid);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("레시피 삭제 과정에서 문제 발생");
			request.setAttribute("error", "레시피가 정상적으로 삭제되지 않았습니다");
			return listCook(request);
		}
		return "redirect:/cook?action=listCook";
	}

	public String listCook(HttpServletRequest request) {
		List<Cook> list;
		try {
			list = dao.getAll();
			request.setAttribute("cooklist", list);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("레시피 목록 생성 과정에서 문제 발생");
			request.setAttribute("error", "레시피 목록이 정상적으로 처리되지 않았습니다.");
		}
		return "project/cookList.jsp";
	}

	public String getCook(HttpServletRequest request) {
		int aid = Integer.parseInt(request.getParameter("aid"));

		try {
			Cook c = dao.getCook(aid);
			request.setAttribute("cook", c);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("레시피를 가져오는 과정에서 문제 발생");
			request.setAttribute("error", "레시피를 정상적으로 가져오지 못했습니다.");
		}
		return "project/cookView.jsp";
	}

	private String getFilename(Part part) {
		String fileName = null;
		String header = part.getHeader("content-disposition");
		System.out.println("Header => " + header);
		int start = header.indexOf("filename=");
		fileName = header.substring(start + 10, header.length() - 1);
		ctx.log("파일명: " + fileName);
		return fileName;
	}

	public void getSearchCook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		List<String> cookList = rc.getSearchCook(keyword);
		request.setAttribute("cooks", cookList);
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { String keyword =
	 * request.getParameter("keyword"); String title=request.getParameter("title");
	 * String newUrl= request.getParameter("newUrl");
	 * 
	 * PrintWriter out = response.getWriter();
	 * out.println("레시피: "+title+"<br>"+newUrl+"<br>"); out.close();CookDAO dao =
	 * new CookDAO(); List<String> cookList = dao.getSearchCook(keyword);
	 * request.setAttribute("cooks", cookList);
	 * request.getRequestDispatcher("search.jsp").forward(request, response); }
	 */
}
