<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="result" class="display.ResultBean" scope="session"/>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>確認ページ</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 20%;
        }
        .button {
            margin: 10px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }
        .button-yes {
            background-color: #4CAF50;
            color: white;
        }
        .button-no {
            background-color: #f44336;
            color: white;
        }
    </style>
</head>
<body>
<%
	String name = result.getName() ;
	String date = result.getDate();;
	String event = result.getEvent() + "(" + result.getAddress() + ")";
	String str = result.getAddress() + "***" + result.getName();
%>
    <p>以下の内容で決定します.よろしいですか?</p>
    <p><%= name %></p>
    <p><%= date %></p>
    <p><%= event %></p>
    <FORM method="GET" action="../ServletForBackHome/">
		<div>
			<label>
				<input type="button" class="button button-yes" value="はい" onclick="alert('保存しました!')">
				<input type="hidden" name="destination" value=<%= str %>/>
			</label>
		</div>
  	</FORM>
    <button class="button button-no" onclick="history.back()">いいえ</button>
</body>
</html>
