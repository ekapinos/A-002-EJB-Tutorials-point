package local.kapinos.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.kapinos.ejb.NewsEntityFacade;
import local.kapinos.ejb.SessionManagerBean;
import local.kapinos.jpa.NewsEntity;

@WebServlet(name = "ListNews", urlPatterns = {"/ListNews"})
public class ListNews extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
    private SessionManagerBean sessionManagerBean;
    @EJB
    private NewsEntityFacade newsEntityFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
    	
    	request.getSession(true);
        
    	response.setContentType("text/html;charset=UTF-8");
    	
    	out.println("<body>");
    	out.println("<h1>@WebServlet ListNews at " + request.getContextPath () + "</h1>");

    	List<NewsEntity> news = newsEntityFacade.findAll();
    	for (Iterator<NewsEntity> it = news.iterator(); it.hasNext();) {
    	    NewsEntity elem = it.next();
    	    out.println(" <b>"+elem.getTitle()+" </b><br />");
    	    out.println(elem.getBody()+"<br /> ");
    	}
    	out.println("<br><br>");
    	out.println("<form action='PostMessage' method='post'>");
    	out.println("<b>Add new message</b><br>");
    	out.println("Title <input type='text' name='title'><br>");
    	out.println("Body  <input type='text' name='body'><br>");
    	out.println("<input type='submit' value='submit' >");
    	out.println("</form>");

    	out.println("<br><br>");
    	out.println(sessionManagerBean.getActiveSessionsCount() + " user(s) reading the news.");

    	out.println("</body>");
    }

}
