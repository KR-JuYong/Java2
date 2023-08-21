package ch11;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ListenerExam implements ServletContextListener, ServletContextAttributeListener, HttpSessionListener, HttpSessionAttributeListener {

    public ListenerExam() {
   
    }

    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	se.getSession().getServletContext().log("Session 생성됨:"+se.getSession().getId());
    }

    public void attributeAdded(ServletContextAttributeEvent scae)  { 
         // TODO Auto-generated method stub
    	scae.getServletContext().log("ServletContext 속성 추가: "+scae.getValue());
    }

    public void attributeRemoved(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }

    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }


    public void attributeAdded(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    	se.getSession().getServletContext().log("Session 속성 추가: "+se.getValue());
    }

    public void attributeRemoved(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

    public void attributeReplaced(HttpSessionBindingEvent event)  { 
         // TODO Auto-generated method stub
    }

    public void attributeReplaced(ServletContextAttributeEvent event)  { 
         // TODO Auto-generated method stub
    }


    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	sce.getServletContext().log("ServletContext 시작됨.");
    }
	
    public void contextDestroyed(ServletContextEvent sce)  { 
        // TODO Auto-generated method stub
    	sce.getServletContext().log("ServletContext 종료됨.");
   }
}
