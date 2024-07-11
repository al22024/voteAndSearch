import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbtest.ProjectInfo;
import dbtest.UserAndProjectInfo;
import usedb.VoteBean;

public class VoteServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
	    HttpSession session = request.getSession(true);
	    session.setAttribute("userID", 1);
	    session.setAttribute("projectID", 1);
	    int userID = (int)session.getAttribute("userID");
	    int projectID = (int)session.getAttribute("projectID");
//	    int userID = 1;
//	    int projectID = 1;
	    
	    UserAndProjectInfo upinfo = new UserAndProjectInfo();
	    ProjectInfo pinfo = new ProjectInfo();
	    
	    upinfo.genre = request.getParameter("GENRE");
	    if(pinfo.getProjectInfo(projectID).category.equals("event")) {
	    	upinfo.updateVoteInfo(userID, projectID);
	    }else {
	    	upinfo.budget1 = request.getParameter("BUDGET1");
		    upinfo.budget2 = request.getParameter("BUDGET2");		    
		    upinfo.review = request.getParameter("REVIEW");
			upinfo.updateVoteInfo(userID, projectID);
	    }
	    
	    VoteBean vb = new VoteBean();
	    vb.setGenre(userID, projectID);
	    vb.setBudget1(userID, projectID);
	    vb.setBudget2(userID, projectID);
	    vb.setReview(userID, projectID);
	    vb.setDateTime(projectID);
	    vb.setVoterList(projectID);
	    request.setAttribute("vb", vb);        
	    	    
	    if(pinfo.getProjectInfo(projectID).managerID != userID) {	        
	        String url="/checkVoteMember.jsp";
	    	RequestDispatcher dispatcher
	                       =getServletContext().getRequestDispatcher(url);
	    	dispatcher.forward(request, response);
	    } else {
	        String url="/checkVoteManager.jsp";
	    	RequestDispatcher dispatcher
	                       =getServletContext().getRequestDispatcher(url);
	    	dispatcher.forward(request, response);
	    }
    }
}