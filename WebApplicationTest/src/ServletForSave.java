import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbtest.*;
import display.*;

public class ServletForSave extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	// Get引数から「名前」をとってくる
    	String name = request.getParameter("name");
    	String destination = request.getParameter("destination");
    	String date = request.getParameter("date");
    	String event = request.getParameter("event");

    	// Beanを作る（「名前」から「メッセージ」を作る）
    	SaveBean save = new SaveBean();
    	


    	// セッションの属性として永続化されているBeanを登録する．
    	HttpSession session = request.getSession(true);
    	int projectID = (int)session.getAttribute("projectID");
    	ProjectInfo projectinfo = new ProjectInfo();
		ProjectInfo pi = projectinfo.getProjectInfo(projectID);
		pi.destination = destination;
		pi.projectID = projectID;
		pi.updateProjectInfo();
		
    	session.setAttribute("save", save);
    	save.setName(name);
    	save.setDestination(destination);
    	save.setEvent(event);
    	save.setDate(date);

    	// このサーブレットの入出力を渡して，JSPを呼び出す．
    	// Beanに設定だれたデータをJSPの中で使う．
    	String url="/Save.jsp";
    	RequestDispatcher dispatcher
                   = getServletContext().getRequestDispatcher(url);
    	dispatcher.forward(request, response);
    }
}