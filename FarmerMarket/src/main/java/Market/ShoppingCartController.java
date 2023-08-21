package Market;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShoppingCartController
 */
@WebServlet("/ShoppingCartController")
public class ShoppingCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String START_PAGE = ".jsp";
	private ServletContext ctx;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		Method m;
		String view = null;

		if (action == null) {
			action = "listShoppingCart";
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

	// 쇼핑카드 목록 보여주기
	public String listShoppingCart(HttpServletRequest request) throws Exception {
		List<ShoppingCart> list;
		ShoppingCartDAO dao = new ShoppingCartDAO();
		try {
			list = dao.getAll();
			request.setAttribute("shoppingcartlist", list);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("쇼핑카트 목록 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "쇼핑카트 목록이 정상적으로 생성되지 않았습니다");
		}
		return "market/cartitem.jsp";// jsp경로추가
	}
	
	// 쇼핑카트 목록 삭제
		public String CancleShoppingCart(HttpServletRequest request) throws Exception {
			int cartid = Integer.parseInt(request.getParameter("cartId"));
			ShoppingCartDAO dao = new ShoppingCartDAO();
			try {
				dao.CancleShoppingCart(cartid);
			} catch (Exception e) {
				e.printStackTrace();
				ctx.log("주문 내역 삭제에 문제가 생겼습니다.");
				request.setAttribute("error", " 정상적으로 삭제되지 않았습니다");
				return listShoppingCart(request);
			}
			return "redirect:/ShoppingCartController?action=listShoppingCart";// 경로 수정
		}
}
