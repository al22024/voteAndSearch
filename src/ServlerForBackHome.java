import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbtest.ProjectInfo;

public class ServlerForBackHome extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	HttpSession session = request.getSession(true);
    	int projectID = (int)session.getAttribute("projectID");
    	
    	String destination = request.getParameter("destination");
    	
    	ProjectInfo pi = new ProjectInfo(projectID);
    	pi.progressStatus = "Notification";
    	Notification noti = new Notification(projectID);
    	pi.updateProjectInfo();
    	// Beanに設定だれたデータをJSPの中で使う．
    	String url="/HomeForTest.html";
    	//要修正
    	RequestDispatcher dispatcher
                   = getServletContext().getRequestDispatcher(url);
    	dispatcher.forward(request, response);
    }
}