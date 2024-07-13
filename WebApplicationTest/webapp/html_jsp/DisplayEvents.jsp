<%@ page contentType="text/html; charset=UTF-8" import="java.util.*" import="display.*" %>
<jsp:useBean id="deb" class="display.DisplayEventsBean" scope="session"/>
<html lang="ja">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <title>検索結果表示</title>
    <style>
        table {
            width: 50%;
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
    <h1><jsp:getProperty name="deb" property="name"/>
	の投票結果に基づく検索結果表示</h1>
	<%
	List<Event> list = deb.getList();
	String event;
	String[] s_address = new String[5];
	String[] s_name = new String[5];
	String[] s_date = new String[5];
	%>
	<table>
		<thead>
			<tr>
				<th>No.</th>
				<th>イベント名</th>
				<th>説明</th>
				<th>住所</th>
				<th>期間</th>
				<th>投票</th>
			</tr>
		</thead>
		<tbody>
	<%
	for (int i = 0; i < list.size(); i++){
		Event e = list.get(i);
		String d = e.startDate + "-" + e.endDate;
		s_name[i] = e.name;
		s_address[i] = e.location;
		s_date[i] = d;
		String mes = i + "に投票する";
	%>
		<tr>
			<td><%= i %></td>
			<td><%= e.name %></td>
			<td><%= e.description%></td>
			<td><%= e.location %></td>
			<td><%= d %></td>
			<td>
				<FORM method="GET" action="../ServletForResult/">
					<div align="center">
						<label>
							<input type="submit" value=<%= mes %>/>
							<input type="hidden" name="name" value=<%= s_name[i] %> />
							<input type="hidden" name="address" value=<%= s_address[i] %> />
							<input type="hidden" name="event" value=<%= event %> />
							<input type="hidden" name="date" value=<%= date %> />
						</label>
					</div>
  				</FORM>
  			</td>
  		</tr>
	<%
	}
	%>
		</tbody>
	</table>
	
	
   
</body>
</html>