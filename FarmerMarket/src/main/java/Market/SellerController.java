package Market;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
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


/**
 * Servlet implementation class SellerController
 */
@WebServlet("/sellerCt")
@MultipartConfig(maxFileSize=1024*1024*2, location="c:/Temp/img")
public class SellerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SellerDAO dao;
	private ServletContext ctx;
	
	private final String START_PAGE = "market/sellerPage.jsp";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao= new SellerDAO();
		ctx = getServletContext();
	}

	
	
	
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		dao = new SellerDAO();

		Method m;
		String view = null;

		if (action == null) {
			action = "listProduct";
			//action = "listOrders";
		} 
		/*
		if (action.equals("listProduct")) {
		    view = listProduct(request);
		} else if (action.equals("listOrders")) {
		    view = listOrders(request);
		}*/
		
		
		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);
			
			view = (String)m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못되었습니다.");
			view = START_PAGE;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(view.startsWith("redirect:/")) {
			String rview = view.substring("redirect:/".length());
			response.sendRedirect(rview);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(view);
			dispatcher.forward(request, response);
		}
	
		
	
	}
	
	public String addProduct (HttpServletRequest request) {
		Market n = new Market();
		
		try {
			Part part = request.getPart("file");
			String fileName = getFilename(part);
			if(fileName != null && !fileName.isEmpty()) {
				part.write(fileName);
			}
			
			BeanUtils.populate(n, request.getParameterMap());
			
			n.setImg("/img/"+fileName);
			
			dao.addProduct(n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("상품 추가 과정에 문제가 발생했습니다.");
			request.setAttribute("error", "상품이 등록되지 않았습니다.");
			return listProduct(request);
		
		}
		return "redirect:/sellerCt?action=listProduct";
	}
	
	
	
	
	public String deleteProduct(HttpServletRequest request) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		try {
			dao.delProduct(pid);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("삭제에 문제가 생겼습니다.");
			request.setAttribute("error", " 정상적으로 삭제되지 않았습니다");
			return listProduct(request);
		}
		return "redirect:/sellerCt?action=listProduct";
	}	
	
	
	public String listProduct (HttpServletRequest request) {
		List<Market> list; 
		try {
			list = dao.getAll();
			request.setAttribute("productlist", list);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("목록 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "목록이 정상적으로 처리되지 않았습니다");
		}
		return START_PAGE;
	}
	
	
	
	public String getProduct (HttpServletRequest request) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		try {
			Market n = dao.getProduct(pid);
			request.setAttribute("product", n); //view 이름과 일치 해야함 
			
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("목록을 가져오는데 문제가 생겼습니다.");
			request.setAttribute("error", "목록을 가져오지 못했습니다.");
		}
		return "market/sellerPage.jsp";//상세 페이지로 들어감 루트수정
	}
	
	
	private String getFilename (Part part) {
		String fileName = null;
		
		String header = part.getHeader("content-disposition");
		System.out.println("Header => " +header);
		
		int start = header.indexOf("filename=");
		fileName = header.substring(start+10, header.length()-1);
		ctx.log("파일명 : "+fileName);
		return fileName;
	}

	/*public String listOrders (HttpServletRequest request) {
		List<Orders> list;
		dao = new SellerDAO();
		try {
			list = dao.getAllorders();
			request.setAttribute("orderslist", list);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("주문 내역 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "주문 내역이 정상적으로 생성되지 않았습니다");
		}
		return "market/sellerPage.jsp";// jsp경로추가
	}*/
	
	
	public String orderslist (HttpServletRequest request) {
		
		return "redirect:/sellerCt?action=getOrders";
	}

	
	public String getOrders(HttpServletRequest request) {
		
		return "redirect:/OrederCt?action=listOrders";
		
	}
	public String MasterLogin (HttpServletRequest request) {
		MarketDAO mdao = new MarketDAO();
		OrdersDAO odao = new OrdersDAO();
		
		List<Orders> olist = new ArrayList<>();
		List<Market> plist = new ArrayList<>();
		
		try {
			plist = mdao.getAll();
			request.setAttribute("productlist", plist);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("상품 목록 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "상품 ㄴ목록이 정상적으로 처리되지 않았습니다");
		}
		
		try {
			olist = odao.getAll();
			request.setAttribute("orderslist", olist);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("주문 내역 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "주문 내역이 정상적으로 생성되지 않았습니다");
		}
		
		return "market/sellerPage.jsp";
	}
	

}
