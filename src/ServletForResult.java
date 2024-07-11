import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbtest.ProjectInfo;
import display.ResultBean;

public class ServletForResult extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	// Get引数から「名前」をとってくる
    	String rating = request.getParameter("rating");
    	String name = request.getParameter("name");
    	String address = request.getParameter("address");
    	String date = request.getParameter("date");
    	String event = request.getParameter("event");

    	// Beanを作る（「名前」から「メッセージ」を作る）
    	ResultBean result = new ResultBean();
    	


    	// セッションの属性として永続化されているBeanを登録する．
    	HttpSession session = request.getSession(true);
    	int projectID = (int)session.getAttribute("projectID");
    	ProjectInfo projectinfo = new ProjectInfo();
		ProjectInfo pi = projectinfo.getProjectInfo(projectID);
		name = pi.projectName;
		date = "" + pi.dateTime;
		
    	session.setAttribute("result", result);
    	result.setRating(rating);
    	result.setName(name);
    	result.setAddress(address);
    	result.setEvent(event);
    	result.setDate(date);

    	// このサーブレットの入出力を渡して，JSPを呼び出す．
    	// Beanに設定だれたデータをJSPの中で使う．
    	String url="/Result.jsp";
    	RequestDispatcher dispatcher
                   = getServletContext().getRequestDispatcher(url);
    	dispatcher.forward(request, response);
    }
}