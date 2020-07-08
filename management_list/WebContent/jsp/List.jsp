<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="list.ListBL" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住所録一覧</title>

<style>
<%@ include file="../css/list.css" %>
</style>


</head>
<body>
<%
request.setCharacterEncoding("UTF-8");
%>

<%@ page import="java.sql.ResultSet" %>

<!-- 変数の宣言 -->
<%!
ResultSet rs = null;
int listCnt = 0;
String nowPage = null;
int maxPage = 0;
%>

<!-- それぞれの処理 -->
<%

nowPage = (String) request.getAttribute("Page");

listCnt = (int) request.getAttribute("listCnt");

maxPage = listCnt / 10;


if (listCnt % 10 != 0) {
	maxPage += 1;
}

rs = (ResultSet) request.getAttribute("Result");


%>


<!-- 表示画面のコード -->

<h1>住所録管理システム：住所録一覧</h1>
<input id="add" type="button" onclick="location.href='./jsp/Add.jsp'" value="新規登録">

<!-- 検索用テキストボックス -->

<form method="get" class="serchbox">
	<table style="float: right" class="serchtable">
		<tr>
			<td><label for="jyusyo">住所：</label></td>
			<td><input id="jyusyo" type="text" name="Serchname" class="serchtext"><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="検索" class="button"></td>
		</tr>
	</table>
</form>

<br><br>

<!-- ページング処理を書くよ！ -->
<form method="get" class="paging">
	<ul>
	<!-- 最初に飛ぶ、一段下がる部分 -->
		<%if (Integer.parseInt(nowPage) == 1) { %>
			<li>&lt;&lt;</li>
			<li>&lt;</li>
		<%} else { %>
			<li><a href="/management_list/ListBL?Page=1">&lt;&lt;</a></li>
			<li><a href="/management_list/ListBL?Page=<%=Integer.parseInt(nowPage) - 1 %>">&lt;</a></li>
		<%} %>

		<!-- 数字で移動する部分 -->
		<%if (Integer.parseInt(nowPage) == 1 || Integer.parseInt(nowPage) == 2) { %>
			<%for (int i = 1; i < 6; i++) {%>
				<%if (Integer.parseInt(nowPage) == i) { %>
					<li><%=i %></li>
				<%} else { %>
					<li><a href="/management_list/ListBL?Page=<%=i %>"><%=i %></a></li>
				<%} %>
				<%if (i != 5) {%>
					<li>|</li>
				<%} %>
			<%} %>

		<%} else if (Integer.parseInt(nowPage) == maxPage || Integer.parseInt(nowPage) == maxPage - 1) { %>
			<%for (int i = maxPage - 4; i <= maxPage; i++) { %>
				<%if (Integer.parseInt(nowPage) == i) {%>
					<li><%=i%></li>
				<%} else {%>
					<li><a href="/management_list/ListBL?Page=<%=i %>"><%=i%></a></li>
				<%} %>
				<%if (maxPage != i) {%>
					<li>|</li>
				<%} %>
			<%}%>

		<%} else { %>
			<%for (int i = Integer.parseInt(nowPage) -2; i < Integer.parseInt(nowPage) + 3; i++) { %>
				<%if (Integer.parseInt(nowPage) == i) { %>
					<li><%=i %></li>
				<%} else { %>
					<li><a href="/management_list/ListBL?Page=<%=i %>"><%=i%></a></li>
				<%} %>
				<%if (Integer.parseInt(nowPage) + 2 != i) {%>
					<li>|</li>
				<%} %>
			<%} %>
		<%} %>

<!-- 最後に飛ぶ、一段上がる部分 -->
		<%if (Integer.parseInt(nowPage) == maxPage) { %>
			<li>&gt;</li>
			<li>&gt;&gt;</li>
		<%} else { %>
			<li><a href="/management_list/ListBL?Page=<%=Integer.parseInt(nowPage) + 1%>">&gt;</a></li>
			<li><a href="/management_list/ListBL?Page=<%=maxPage %>">&gt;&gt;</a></li>
		<%} %>
	</ul>
</form>


<!-- DB表示するテーブル部分 -->

<table class="listcss">
	<tr>
		<th width="30px" >No.</th>
		<th width="150px" >名前</th>
		<th width="380px" >住所</th>
		<th width="200px">電話番号</th>
		<th width="80px" ><font color="#c71585f">カテゴリ</font></th>
		<th></th>
	</tr>
<% while (rs.next()) { %>

	<tr>
		<td><p><%=rs.getInt("id") %></p></td>
    	<td><p><%=rs.getString("name") %></p></td>
    	<td><p class="threereader"><%=rs.getString("address") %><span class="tooltip"><%=rs.getString("address") %></span></p></td>
    	<%if (rs.getString("tel").equals("")) { %>
    		<td>&nbsp;</td>
    	<%} else { %>
    		<td><p><%=rs.getString("tel").substring(0, 3) + "-" + rs.getString("tel").substring(3, 7) + "-" + rs.getString("tel").substring(7, 11)%></p></td>
    	<%} %>
    	<td><p><font color="#c71585f"><%=rs.getString("categoryname") %></font></p></td>
    	<td>
    	<form method="post">
    	<input type="hidden" name="id" value="<%=rs.getInt("id") %>">
    	<input type="hidden" name="name" value="<%=rs.getString("name") %>">
    	<input type="hidden" name="address" value="<%=rs.getString("address") %>">
    	<input type="hidden" name="tel" value="<%=rs.getString("tel").substring(0, 3) + "-" + rs.getString("tel").substring(3, 7) + "-" + rs.getString("tel").substring(7, 11) %>">
    	<input type="hidden" name="categoryname" value="<%=rs.getString("categoryname") %>">
    	<input type="submit" class="button2" formaction="./jsp/Edit.jsp" value="編集"><input type="submit" class="button2" formaction="./jsp/Delete.jsp" value="削除"></form></td>
	</tr>
<% } %>
</table>

<!-- ページング処理を書くよ！ -->
<form method="get">
	<ul>
		<!-- 最初に飛ぶ、一段下がる部分 -->
		<%if (Integer.parseInt(nowPage) == 1) { %>
			<li>&lt;&lt;</li>
			<li>&lt;</li>
		<%} else { %>
			<li><a href="/management_list/ListBL?Page=1">&lt;&lt;</a></li>
			<li><a href="/management_list/ListBL?Page=<%=Integer.parseInt(nowPage) - 1 %>">&lt;</a></li>
		<%} %>

		<!-- 数字で移動する部分 -->
		<%if (Integer.parseInt(nowPage) == 1 || Integer.parseInt(nowPage) == 2) { %>
			<%for (int i = 1; i < 6; i++) {%>
				<%if (Integer.parseInt(nowPage) == i) { %>
					<li><%=i %></li>
				<%} else { %>
					<li><a href="/management_list/ListBL?Page=<%=i %>"><%=i %></a></li>
				<%} %>
				<%if (i != 5) {%>
					<li>|</li>
				<%} %>
			<%} %>

		<%} else if (Integer.parseInt(nowPage) == maxPage || Integer.parseInt(nowPage) == maxPage - 1) { %>
			<%for (int i = maxPage - 4; i <= maxPage; i++) { %>
				<%if (Integer.parseInt(nowPage) == i) {%>
					<li><%=i%></li>
				<%} else {%>
					<li><a href="/management_list/ListBL?Page=<%=i %>"><%=i%></a></li>
				<%} %>
				<%if (maxPage != i) {%>
					<li>|</li>
				<%} %>
			<%}%>
		<%} else { %>
			<%for (int i = Integer.parseInt(nowPage) -2; i < Integer.parseInt(nowPage) + 3; i++) { %>
				<%if (Integer.parseInt(nowPage) == i) { %>
					<li><%=i %></li>
				<%} else { %>
					<li><a href="/management_list/ListBL?Page=<%=i %>"><%=i%></a></li>
				<%} %>
				<%if (Integer.parseInt(nowPage) + 2 != i) {%>
					<li>|</li>
				<%} %>
			<%} %>
		<%} %>

		<!-- 最後に飛ぶ、一段上がる部分 -->
		<%if (Integer.parseInt(nowPage) == maxPage) { %>
			<li>&gt;</li>
			<li>&gt;&gt;</li>
		<%} else { %>
			<li><a href="/management_list/ListBL?Page=<%=Integer.parseInt(nowPage) + 1%>">&gt;</a></li>
			<li><a href="/management_list/ListBL?Page=<%=maxPage %>">&gt;&gt;</a></li>
		<%} %>
	</ul>
</form>

<input id="add" type="button" onclick="location.href='./jsp/Add.jsp'" value="新規登録" class="top">

</body>

</html>