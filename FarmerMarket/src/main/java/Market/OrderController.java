package Market;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrederController
 */
@WebServlet("/OrederCt")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String START_PAGE = "market/sellerPage.jsp";
	private ServletContext ctx;
	private OrdersDAO dao;
	
	
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new OrdersDAO ();
		ctx = getServletContext();
	}
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		dao = new OrdersDAO();

		Method m;
		String view = null;

		if (action == null) {
			action = "listOrders";
			
		}

		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class);

			view = (String) m.invoke(this, request);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못되었습니다.");
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
	// 주문 내역 보여주기
	public String listOrders(HttpServletRequest request) {
		List<Orders> list;
		dao = new OrdersDAO();
		try {
			list = dao.getAll();
			request.setAttribute("orderslist", list);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("주문 내역 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "주문 내역이 정상적으로 생성되지 않았습니다");
		}
		return "market/sellerPage.jsp";// jsp경로추가
	}

	
	
	
	// 주문 내역 상세 보기
	public String getOrders(HttpServletRequest request) {
		int orderid = Integer.parseInt(request.getParameter("orderId"));
		dao = new OrdersDAO();
		try {
			Orders o = dao.getOrder(orderid);
			request.setAttribute("orders", o); // view 이름과 일치 해야함

		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("주문 내역 상세 정보를 가져오는데 실패했습니다.");
			request.setAttribute("error", "상세 정보를 불러오지 못했습니다.");
		}
		return "market/sellerPage.jsp";// jsp경로 추가
	}
	
	
	
	
	

	// 주문 내역 삭제
	public String CancleOrders(HttpServletRequest request) {
		int orderid = Integer.parseInt(request.getParameter("orderid"));
		dao = new OrdersDAO();
		try {
			dao.CancleOrders(orderid);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("주문 내역 삭제에 문제가 생겼습니다.");
			request.setAttribute("error", " 정상적으로 삭제되지 않았습니다");
			return listOrders(request);
		}
		return "redirect:/OrederCt?action=listOrders";// 경로 수정
	}

	// 주문 내역 추가
	/*public String addOrders(HttpServletRequest request) {
	    int cartItemId = Integer.parseInt(request.getParameter("cartItemId"));
	    
	    HttpSession session = request.getSession();
	    ShoppingCart sc = (ShoppingCart) session.getAttribute("cart");
	    if (sc == null) {
	        sc = new ShoppingCart();
	        session.setAttribute("cart", sc);
	    }
	    
	    CartItem cartItem = sc.findCartItemById(cartItemId);
	    if (cartItem != null) {
	        Orders order = new Orders();
	        order.setProductId(cartItem.getProductId());
	        
	        // 여기에 주문 내역에 대한 추가 처리 로직을 작성하세요
	        // order 객체를 orders 테이블에 저장하거나 다른 처리를 수행할 수 있습니다
	        
	        sc.removeItem(cartItem); // 주문한 상품은 장바구니에서 제거
	        
	        return "redirect:/OrderCt?action=listOrders";
	    } else {
	        // 주문할 상품을 찾을 수 없는 경우에 대한 예외 처리
	        return "redirect:/CartController?action=viewCart";
	    }
	}*/
}