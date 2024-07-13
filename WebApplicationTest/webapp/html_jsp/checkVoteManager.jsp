<!--*******************************************************************
*** File Name : checkVoteManager.jsp
*** Version : V1.0
*** Designer : 相内 優真
*** Date : 2024.07.01
*** Purpose : W7 投票内容画面(幹事)を表示する
***
*******************************************************************-->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<% List<List<String>> voterList = (List<List<String>>)request.getAttribute("voterList"); 
   String dateTime = (String)request.getAttribute("dateTime");
   String genre = (String)request.getAttribute("genre");
   String budget1 = (String)request.getAttribute("budget1");
   String budget2 = (String)request.getAttribute("budget2");
   String review = (String)request.getAttribute("review"); 
   String month = dateTime.substring(5, 7);
   String date = dateTime.substring(8, 10); 
%>

<% String yet = voterList.get(1).get(0); %>

<html>
    <head>
        <!--外部のCSSファイルを読み込み-->
        <link rel="stylesheet" href="./pageStyle.css">

        <style>
            div #content {
                width: 760px;
                overflow: hidden;
            }

            div #check {
                width: 380px;
                float: left;
                padding: 0px 170px 150px 100px;
                border-right: 3px solid #000;
            }

            nav {
                width: 380px;
                float: right;
                padding: 0px 100px 100px 100px;
            }
        </style>
    </head>
    <body>
        <div id="content">
            <!--投票内容を確認する部分を追加(画面の左半分)-->
            <div id="check">
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
            </div>

            <!--投票状況を確認する部分を追加(画面の右半分)-->
            <nav>
                <p>投票状況</p>

                <!--投票状況の表を追加-->
                <table border = "1">
                <tr>
                <th>未投票</th>
                <th>投票済み</th>
                </tr>
                <%
                for(int i=0;i<voterList.get(0).size();i++){
                    out.println("<tr>");
                        for(int j=0;j<2;j++){
                            out.println("<td>" + voterList.get(j).get(i) + "</td>");
                        }
                    out.println("</tr>");
                }
                %>
                </table>

                <br>

                <!--投票締め切りボタンの追加 （押したら検索のサーブレットのURL フィールドの値 ISEVENT）-->
                <FORM method="GET" action="../ServletForDisplay/">
                    <% if(budget1 != null){
                        out.println("<input type=\"hidden\" name=\"ISEVENT\" value=\"0\">");
                       }else{
                        out.println("<input type=\"hidden\" name=\"ISEVENT\" value=\"1\">");
                       } %>
                    <button type="submit">投票締め切り</button>
                </FORM>
            </nav>
        </div>
    </body>
</html>
