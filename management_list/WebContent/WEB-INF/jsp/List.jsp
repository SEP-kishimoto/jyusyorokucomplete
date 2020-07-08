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

if (listCnt % 10 == 0) {
	maxPage = listCnt;
} else {
	maxPage = 1;
}

rs = (ResultSet) request.getAttribute("Result");

%>

<!-- 表示画面のコード -->

<h1>住所録管理システム：住所録一覧</h1>
<input id="add" type="button" onclick="location.href='./Add.jsp'" value="新規登録">

<!-- 検索用テキストボックス -->
<form method="POST">
住所：<input type="text" name="address"><br>
<input type="submit" style="" value="検索" name="Serchname">
</form>

<!-- DB表示するテーブル部分 -->
<table border="1">
	<tr>
		<th>No.</th>
		<th>名前</th>
		<th>住所</th>
		<th>電話番号</th>
		<th>カテゴリ</th>
		<th></th>
	</tr>
<% while (rs.next()) { %>
	<tr>
		<td><%=rs.getInt("id") %></td>
    	<td><%=rs.getString("name") %></td>
    	<td><%=rs.getString("address") %></td>
    	<td><%=rs.getString("tel") %></td>
    	<td><%=rs.getString("categoryname") %></td>
    	<td><input type="submit" onclick="location.href='./Edit.jsp'" value="編集"><input type="submit" onclick="location.href='./Delete.jsp'"value="削除"></td>
	</tr>
<% } %>
</table>
<input id="add" type="button" onclick="location.href='./Add.jsp'" value="新規登録">

</body>

</html>