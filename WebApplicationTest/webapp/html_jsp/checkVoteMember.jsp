<!--*******************************************************************
*** File Name : checkVoteMember.jsp
*** Version : V1.0
*** Designer : 相内 優真
*** Date : 2024.6.30
*** Purpose : W7 投票内容画面(メンバー)を表示する
***
*******************************************************************-->

<%@ page contentType="text/html; charset=UTF-8" %>
<% String dateTime = (String)request.getAttribute("dateTime");
   String genre = (String)request.getAttribute("genre");
   String budget1 = (String)request.getAttribute("budget1");
   String budget2 = (String)request.getAttribute("budget2");
   String review = (String)request.getAttribute("review"); 
   String month = dateTime.substring(5, 7);
   String date = dateTime.substring(8, 10); 
   %>

<html>
    <head>
        <!--外部のCSSファイルを読み込み-->
        <link rel="stylesheet" href="./pageStyle.css">
    </head>
    <body>
        <p>投票内容確認</p>
        <p>日程： <%= month %>月<%= date %>日</p>

        <!--投票した内容を表示する-->
        <pre>
        ジャンル
         <%= genre %>

         <% if(budget1 != null){
            out.println("予算\n" + budget1 + "～" + budget2 + "\n\n" + "レビュー評価\n" + review);
         } %>
        </pre>

        <br>

        <!--投票内容修正ボタンの追加-->
        <button id="vote" onclick="history.back()">投票内容修正</button>
    </body>
</html>
