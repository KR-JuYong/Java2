package market;

import java.io.IOException;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

@WebServlet("/mkCont")
@MultipartConfig(maxFileSize = 1024 * 1024 * 2, location = "c:/Temp/img")
public class MarketController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MarketDAO dao;
	private ServletContext ctx;

	private final String START_PAGE = "market/mainMarket.jsp"; // 시작페이지 지정

	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new MarketDAO();
		ctx = getServletContext();

	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		dao = new MarketDAO();

		Method m;
		String view = null;

		if (action == null) {
			action = "listMarket";
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

	// 로그인 처리
//	public String login(HttpServletRequest request) {
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//
//		// 사용자 인증 및 세션에 사용자 정보 저장 로직
//		boolean isAuthenticated = authenticateUser(username, password); // 사용자 인증 메서드 호출
//
//		if (isAuthenticated) {
//			HttpSession session = request.getSession(true);
//			session.setAttribute("username", username);
//			return "redirect:/mkCont?action=listMarket";
//		} else {
//			request.setAttribute("error", "로그인에 실패했습니다.");
//			return "redirect:/mkCont?action=listMarket"; // 로그인 실패 시 이동할 페이지 설정
//		}
//	}

	// 로그아웃 처리
//	public String logout(HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//
//		if (session != null) {
//			session.invalidate();
//		}
//
//		return "redirect:/mkCont?action=listMarket"; // 로그아웃 후 이동할 페이지 설정
//	}
/*
	private boolean authenticateUser(String email, String password) {
		try {
			// 사용자 정보를 이용하여 데이터베이스에서 해당 사용자를 조회합니다.
			User user = dao.getUserByEmail(email);

			// 사용자 정보가 존재하지 않거나 비밀번호가 일치하지 않으면 인증 실패로 처리합니다.
			if (user == null || !user.getPassword().equals(password)) {
				return false; // 인증 실패
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // 인증 실패
		}

		return true; // 인증 성공
	}
*/

	// 상품추가
/*
	public String addProduct(HttpServletRequest request) {
		Market n = new Market();

		try {
			Part part = request.getPart("file");
			String fileName = getFilename(part);
			if (fileName != null && !fileName.isEmpty()) {
				part.write(fileName);
			}

			BeanUtils.populate(n, request.getParameterMap());

			n.setImg("/img/" + fileName);

			dao.addProduct(n);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("상품 추가 과정에 문제가 발생했습니다.");
			request.setAttribute("error", " 정상적으로 등록되지 않았습니다.");
		}
		return "redirect:/mkCont?action=listMarket";// 경로 수정
	}
*/
	// 상품삭제
	/*
	 * public String deleteProduct(HttpServletRequest request) { int mid =
	 * Integer.parseInt(request.getParameter("mid")); try { dao.delProduct(mid); }
	 * catch (Exception e) { e.printStackTrace(); ctx.log("삭제에 문제가 생겼습니다.");
	 * request.setAttribute("error", " 정상적으로 삭제되지 않았습니다"); return
	 * listMarket(request); } return "redirect:/mkCont?action=listMarket";//경로 수정 }
	 */

	
	public String listMarket(HttpServletRequest request) {
		List<Market> list;
		try {
			list = dao.getAll();
			request.setAttribute("marketlist", list);

		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("상품 목록 생성에 문제가 생겼습니다.");
			request.setAttribute("error", "목록이 정상적으로 생성되지 않았습니다");
		}
		return "market/mainMarket.jsp";// jsp경로추가
	}

	
	public String getProduct(HttpServletRequest request) {
		int pid = Integer.parseInt(request.getParameter("pid"));
		try {
			Market n = dao.getProduct(pid);
			request.setAttribute("product", n); // view 이름과 일치 해야함

		} catch (SQLException e) {
			e.printStackTrace();
			ctx.log("상품정보를 가져오는데 실패했습니다.");
			request.setAttribute("error", "정보를 불러오지 못했습니다.");
		}
		return "market/productView.jsp"; //jsp경로추가
	}

	// 파일가져오기 물건 사진

//	private String getFilename(Part part) {
//		String fileName = null;
//
//		String header = part.getHeader("content-disposition");
//		System.out.println("Header => " + header);
//
//		int start = header.indexOf("filename=");
//		fileName = header.substring(start + 10, header.length() - 1);
//		ctx.log("파일명 : " + fileName);
//		return fileName;
//	}

	// 회원가입 페이지로 이동합니다
//	public String register(HttpServletRequest request) {
//
//		return "redirect:/mkCont?action=farmerlogin";
//	}

	// 회원가입 페이지로 이동하기위해 설정
//	public String farmerlogin(HttpServletRequest request) {
//
//		return "redirect:/userCt?action=farmerlogin";
//	}

//	public String cartitem(HttpServletRequest request) {
//
//		return "redirect:/CartItemController?action=listShoppingCart";
//	}

//	public String mainPage(HttpServletRequest request) {
//		return "market/mainMarket.jsp";
//	}

	/*
	 * //유저 정보 조회 private String getUser (HttpServletRequest request) {
	 * 
	 * try {
	 * 
	 * }catch {
	 * 
	 * } return; }
	 */
/*
	public String getShoppingCart(HttpServletRequest request) {
		int mid = Integer.parseInt(request.getParameter("mid"));

		try {
			dao.getShoppingcart(mid);
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("장바구니에 문제가 생겼습니다.");
			request.setAttribute("error", " 정상적으로 장바구니에 들어가지 않았습니다");
		}
		// return "redirect:/mkCont?action=listMarket";//경로 수정
		return "redirect:/mkCont?action=listMarket";// 경로 다시 설정해야함 초기화면으로 돌아간다.
	}

	// 로그인
	public String MasterLogin(HttpServletRequest request) {

		return "redirect:/sellerCt?action=MasterLogin";
	}

	// 주문내역보여주기
	public String orderlist(HttpServletRequest request) {
		return "redirect:/OrderCt?action=orderslist";
	}

	// 상품목록 보여주기
	public String productlist(HttpServletRequest request) {
		return "redirect:/sellerCt?action=getProduct";
	}
*/
}
