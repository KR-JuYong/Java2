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

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class CartItemController
 */
@WebServlet("/CartItemController")
public class CartItemController extends HttpServlet {
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

		CartItemDAO dao = new CartItemDAO();

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

	// 장바구니 추가
	// 장바구니에 상품 추가
	public String addCartItem(HttpServletRequest request) {
		int productId = Integer.parseInt(request.getParameter("productId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		CartItem ci = new CartItem();
		ci.setProductId(productId);
		ci.setQuantity(quantity);

		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		if (cart == null) {
			cart = new ShoppingCart();
			session.setAttribute("cart", cart);
		}
		cart.addItem(ci);

		return "redirect:/c/CartItemController?action=listCartItem";
	}

	// 장바구니 삭제
	/*public String deleteCartItem(HttpServletRequest request) {
		int cartid = Integer.parseInt(request.getParameter("cartId"));
		CartItemDAO dao = new CartItemDAO();
		try {
			dao.delCartItem(cartid);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("삭제에 문제가 생겼습니다.");
			request.setAttribute("error", " 정상적으로 삭제되지 않았습니다");
			return listShoppingCart(request);
		}
		return "redirect:/CartItemController?action=listCartItem";// 경로 수정
	}*/

	// 장바구니 목록 보여주기
	/*public String listCartItem(HttpServletRequest request) {
		List<CartItem> list;
		CartItemDAO dao = new CartItemDAO();
		try {
			list = dao.getAll();
			request.setAttribute("cartitemlist", list);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("장바구니 목록 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "장바구니 목록이 정상적으로 생성되지 않았습니다");
		}
		return "market/cartitem.jsp";// jsp경로추가
	}
*/
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
	/*
	 * public String getTotalPrice(HttpServletRequest request) { CartItemDAO dao =
	 * new CartItemDAO(); try { int totalPrice = dao.getTotalPrice(); // Remove the
	 * parameter from the method call request.setAttribute("totalPrice",
	 * totalPrice); } catch (Exception e) { e.printStackTrace(); } return
	 * "redirect:/CartItemController?action=listCartItem"; }
	 */
	
}