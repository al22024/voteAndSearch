<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"%>
<jsp:useBean id="display" class="display.DisplayBean" scope="session"/>
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>検索結果表示</title>
    <style>
        table {
            width: 80%;
            border-collapse: collapse;
            margin: 20px;
            font-size: 18px;
            text-align: left;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
        }

    </style>
</head>
<body>
	<% 
	int userID = (int)session.getAttribute("userID");
	int projectID = (int)session.getAttribute("projectID");
	String query = display.getQuery();
	%>
	<%= "userID:" + userID + "\n" %>
	<%= "projectID:" + projectID + "\n" %>
	<%= "Query:" + query + "\n" %>
    <h1><jsp:getProperty name="display" property="name"/>
	の投票結果に基づく検索結果表示</h1>
	<%
	List<String[]> list = display.getList();
	String[] temp;
	String[] s_rating = new String[5];
	String[] s_address = new String[5];
	String[] s_name = new String[5];
	String event = display.getName();
	String date = display.getDate();
	%>
    <table>
        <thead>
            <tr>
                <th>評価順位</th>
                <th>評価値</th>
                <th>店・施設名</th>
                <th>住所</th>
                <th>投票</th>
            </tr>
        </thead>
        <tbody>
            <tr>
            <%
            if (list.size() >= 1){
            	temp = list.get(0);
            	s_rating[0] = temp[2];
            	s_name[0] = temp[0];
            	s_address[0] = temp[1];
            }
            else {
            	s_rating[0] = "";
            	s_name[0] = "";
            	s_address[0] = "";
            }
            %>
                <td>1位</td>
                <td><%= s_rating[0] %></td>
                <td><%= s_name[0] %></td>
                <td><%= s_address[0] %></td>
                <td>
					<FORM method="GET" action="../ServletForResult/">
						<div align="center">
							<label>
								<input type="submit" value="1に投票する"/>
								<input type="hidden" name="rating" value=<%= s_rating[0] %>/>
								<input type="hidden" name="name" value=<%= s_name[0] %> />
								<input type="hidden" name="address" value=<%= s_address[0] %> />
								<input type="hidden" name="event" value=<%= event %> />
								<input type="hidden" name="date" value=<%= date %> />
							</label>
						</div>
  					</FORM>
  				</td>
            </tr>
            <tr>
            <%
            if (list.size() >= 2){
            	temp = list.get(1);
            	s_rating[1] = temp[2];
            	s_name[1] = temp[0];
            	s_address[1] = temp[1];
            }
            else {
            	s_rating[1] = "";
            	s_name[1] = "";
            	s_address[1] = "";
            }
            %>
                <td>2位</td>
                <td><%= s_rating[1] %></td>
                <td><%= s_name[1] %></td>
                <td><%= s_address[1] %></td>
                <td>
					<FORM method="GET" action="../ServletForResult/">
						<div align="center">
							<label>
								<input type="submit" name="RESULT" value="2に投票する"/>
								<input type="hidden" name="rating" value=<%= s_rating[1] %>/>
								<input type="hidden" name="name" value=<%= s_name[1] %> />
								<input type="hidden" name="address" value=<%= s_address[1] %> />
								<input type="hidden" name="event" value=<%= event %> />
								<input type="hidden" name="date" value=<%= date %> />
							</label>
						</div>
  					</FORM>
				</td>
            </tr>
            <tr>
            <%
            if (list.size() >= 3){
            	temp = list.get(2);
            	s_rating[2] = temp[2];
            	s_name[2]= temp[0];
            	s_address[2] = temp[1];
            }
            else {
            	s_rating[2] = "";
            	s_name[2] = "";
            	s_address[2] = "";
            }
            %>
                <td>3位</td>
                <td><%= s_rating[2] %></td>
                <td><%= s_name[2] %></td>
                <td><%= s_address[2] %></td>
                <td>
					<FORM method="GET" action="../ServletForResult/">
						<div align="center">
							<label>
								<input type="submit" name="RESULT" value="3に投票する"/>
								<input type="hidden" name="rating" value=<%= s_rating[2] %>/>
								<input type="hidden" name="name" value=<%= s_name[2] %> />
								<input type="hidden" name="address" value=<%= s_address[2] %> />
								<input type="hidden" name="event" value=<%= event %> />
								<input type="hidden" name="date" value=<%= date %> />
							</label>
						</div>
  					</FORM>
				</td>
            </tr>
            <tr>
            <%
            if (list.size() >= 4){
            	temp = list.get(3);
            	s_rating[3] = temp[2];
            	s_name[3] = temp[0];
            	s_address[3] = temp[1];
            }
            else {
            	s_rating[3] = "";
            	s_name[3] = "";
            	s_address[3] = "";
            }
            %>
                <td>4位</td>
                <td><%= s_rating[3] %></td>
                <td><%= s_name[3] %></td>
                <td><%= s_address[3] %></td>
                <td>
					<FORM method="GET" action="../ServletForResult/">
						<div align="center">
							<label>
								<input type="submit" name="RESULT" value="4に投票する"/>
								<input type="hidden" name="rating" value=<%= s_rating[3] %>/>
								<input type="hidden" name="name" value=<%= s_name[3] %> />
								<input type="hidden" name="address" value=<%= s_address[3] %> />
								<input type="hidden" name="event" value=<%= event %> />
								<input type="hidden" name="date" value=<%= date %> />
							</label>
						</div>
  					</FORM>
				</td>
            </tr>
            <tr>
            <%
            if (list.size() >= 5){
            	temp = list.get(4);
            	s_rating[4] = temp[2];
            	s_name[4] = temp[0];
            	s_address[4] = temp[1];
            }
            else {
            	s_rating[4] = "";
            	s_name[4] = "";
            	s_address[4] = "";
            }
            %>
                <td>5位</td>
                <td><%= s_rating[4] %></td>
                <td><%= s_name[4] %></td>
                <td><%= s_address[4] %></td>
                <td>
					<FORM method="GET" action="../ServletForResult/">
						<div align="center">
							<label>
								<input type="submit" name="RESULT" value="5に投票する"/>
								<input type="hidden" name="rating" value=<%= s_rating[4] %>/>
								<input type="hidden" name="name" value=<%= s_name[4] %> />
								<input type="hidden" name="address" value=<%= s_address[4] %> />
								<input type="hidden" name="event" value=<%= event %> />
								<input type="hidden" name="date" value=<%= date %> />
							</label>
						</div>
  					</FORM>
				</td>
            </tr>
        </tbody>
    </table>
</body>
</html>