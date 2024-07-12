import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbtest.ProjectInfo;
import display.DisplayBean;
import display.DisplayEventsBean;

public class ServletForDisplay extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	// Get引数から「名前」をとってくる
    	HttpSession session = request.getSession(true);
    	String KEY = request.getParameter("MESSAGE");
    	String ISEVENT = request.getParameter("ISEVENT");
    	int projectID = (int)session.getAttribute("projectID");
    	ProjectInfo pi = new ProjectInfo(projectID);
    	pi.progressStatus = "Searching";
    	pi.updateProjectInfo();
    	if (false/*ISEVENT.equals("0")*/) {
    		// Beanを作る（「名前」から「メッセージ」を作る）
        	DisplayBean display = new DisplayBean();
        	display.makeList(projectID);
        	
        	///
        	List<String[]> list = display.getList();
        	///
        	
        	
        	display.setList(list);
        	
        	// セッションの属性として永続化されているBeanを登録する．
        	session.setAttribute("display", display);

        	// このサーブレットの入出力を渡して，JSPを呼び出す．
        	// Beanに設定だれたデータをJSPの中で使う．
        	String url="/Display.jsp";
        	RequestDispatcher dispatcher
                       = getServletContext().getRequestDispatcher(url);
        	dispatcher.forward(request, response);
    	}
    	else {
    		//イベント検索用メソッドの追加とjsp等への遷移の追加やれ
    		DisplayEventsBean deb = new DisplayEventsBean();
    		deb.makeList(projectID);
    		session.setAttribute("deb", deb);
    		String url="/DisplayEvents.jsp";
        	RequestDispatcher dispatcher
                       = getServletContext().getRequestDispatcher(url);
        	dispatcher.forward(request, response);
    	}

    	
    }
}