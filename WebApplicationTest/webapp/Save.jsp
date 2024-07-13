<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="save" class="display.SaveBean" scope="session"/>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>保存完了</title>
</head>
<body>
<%
	String name = save.getName();
	String date = save.getDate();
	String event = save.getEvent();
%>
    <p>以下の内容を保存しました!</p>
    <p><%= event %></p>
    <p><%= date %></p>
    <p><%= name %></p>
    <FORM method="GET" action="../ServletForHome/">
		<div>
			<label>
				<input type="submit" value="ホームへ戻る"/>
			</label>
		</div>
  	</FORM>
</body>
</html>
