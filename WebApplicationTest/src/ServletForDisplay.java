import java.io.*;
import java.net.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import dbtest.*;
import display.*;

public class ServletForDisplay extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
    	// Get引数から「名前」をとってくる
    	HttpSession session = request.getSession(true);
    	//String KEY = request.getParameter("MESSAGE");
    	String ISEVENT = request.getParameter("ISEVENT");
    	int projectID = (int)session.getAttribute("projectID");
    	
    	ProjectInfo PI = new ProjectInfo();
    	ProjectInfo pi = PI.getProjectInfo(projectID);
    	pi.progressstatus = "searching";
    	pi.updateProjectInfo();
    	
    	if (ISEVENT.equals("0")) {
    		// Beanを作る（「名前」から「メッセージ」を作る）
        	DisplayBean display = new DisplayBean();
        	
        	///
        	///
        	UserAndProjectInfo userAndProjectInfo = new UserAndProjectInfo();
        	UserAndProjectInfo UAPI = userAndProjectInfo.getVoteInfo(projectID);
        	display.genre = UAPI.genreList;
        	display.budget1 = UAPI.budget1List;
        	display.budget2 = UAPI.budget2List;
        	display.review = UAPI.reviewList;
        	///
        	///
        	
        	display.makeList(projectID);
        	
        	///
        	List<String[]> list = display.getList();
        	///
        	
        	
        	
        	
        	display.setList(list);
        	
        	if (display.list == null || display.list.size() == 0) {
        		try {
        			pi.progressstatus = "Voting";
        			pi.updateProjectInfo();
        			response.sendRedirect(new URI(request.getHeader("referer")).getPath());
        			return;
        			} catch (URISyntaxException e) {
        			e.printStackTrace();
        			}
        	}
        	
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
    		if (deb.event == null || deb.event.size() == 0) {
        		try {
        			pi.progressstatus = "Voting";
        			pi.updateProjectInfo();
        			response.sendRedirect(new URI(request.getHeader("referer")).getPath());
        			return;
        			} catch (URISyntaxException e) {
        			e.printStackTrace();
        			}
        	}
    		
    		session.setAttribute("deb", deb);
    		String url="/DisplayEventsBean.jsp";
        	RequestDispatcher dispatcher
                       = getServletContext().getRequestDispatcher(url);
        	dispatcher.forward(request, response);
    	}

    	
    }
}