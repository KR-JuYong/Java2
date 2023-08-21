package Market;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Pay")
public class PayController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletContext ctx;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		PayDAO dao = new PayDAO();

		Method m;
		String view = null;

		if (action == null) {
			action = "addPay";
		}

		try {
			m = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
			view = (String) m.invoke(this, request, response);

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			ctx.log("요청 action 없음!!");
			request.setAttribute("error", "action 파라미터가 잘못되었습니다.");
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

	// 결제 정보 입력
	public String addPay(HttpServletRequest request) {
		Pay p = new Pay();
		PayDAO dao = new PayDAO();
		p.setName(request.getParameter("name"));
		p.setPhonenumber(request.getParameter("phonenumber"));
		p.setName2(request.getParameter("name2"));
		p.setPhonenumber2(request.getParameter("phonenumber2"));
		p.setAddress(request.getParameter("address"));
		p.setCardnumber(request.getParameter("cardnumber"));
		p.setCvc(request.getParameter("cvc"));
		p.setDate(request.getParameter("date"));

		try {
			dao.addPay(p);
			return "redirect:/paysuccess.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/payerror.jsp";
		}
	}

	// 총 결제 금액
	public String getTotalPrice(HttpServletRequest request) {

		PayDAO dao = new PayDAO();
		try {
			int totalPrice = dao.getTotalPrice();
			request.setAttribute("totalPrice", totalPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Market/pay.jsp";
	}
}
