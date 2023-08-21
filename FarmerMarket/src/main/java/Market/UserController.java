package Market;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/userCt")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO dao;
	private ServletContext ctx;
	
	private final String START_PAGE = "market/mainMarket.jsp"; //시작페이지 지정
       
    
    /*public UserController() {
        super();
    }*/

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao= new UserDAO();
		ctx = getServletContext();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");

		dao = new UserDAO();

		Method m;
		String view = null;

		if (action == null) {
			action = "listMarket";
		}
		
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

	public String register(HttpServletRequest request) {
	    User u = new User();

	    try {
	        BeanUtils.populate(u, request.getParameterMap());

	        dao.register(u); // 수정: dao.addUser(u) -> dao.register(u)
	    } catch (Exception e) {
	        e.printStackTrace();
	        ctx.log("회원가입 과정에 문제가 발생했습니다.");
	        request.setAttribute("error", "정상적으로 등록되지 않았습니다.");
	        return farmerlogin(request);
	    }
	    return "redirect:/mkCont?action=listMarket";
	}

	
	public String farmerlogin(HttpServletRequest request) {
		
	    
	    return "market/farmerLogin.jsp"; // 리스트마켓을 보여주는 JSP 페이지 경로를 반환합니다.
	}

}
