<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page
import="jyusyoroku.AddBL"
import="common.Common"
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住所録削除</title>
<style>
<%@ include file="../css/delete.css" %>
</style>
</head>

<body>

<!-- 文字コード -->
<%
request.setCharacterEncoding("UTF-8");
%>


<!-- 変数の宣言 -->
<%@ page import="java.sql.ResultSet" %>
<%
String id = "";
String name = "";
String address = "";
String tel = "";
String category = "";
ResultSet rs = null;
%>

<!-- 各設定を行う -->
<%
id = request.getParameter("id");
name = request.getParameter("name");
address = request.getParameter("address");
tel = request.getParameter("tel");
category = request.getParameter("categoryname");
%>

<!-- 画面作成 -->
<form method="post">
	<p>下記住所録を削除します。よろしいですか？</p><br>
	<input type="hidden" value="<%=id %>" name="id">
	<dl>
		<dt>名前</dt><dd><span>:&nbsp;</span><%=name %><input type="hidden" value="<%=name %>" name="name"></dd>
		<dt>住所</dt><dd><span>:&nbsp;</span><%=address %><input type="hidden" value="<%=address %>" name="address"></dd>
		<dt>電話番号</dt><dd><span>:&nbsp;</span><%=tel.substring(0, 3) + tel.substring(3, 7) + tel.substring(7, 11) %><input type="hidden" value="<%=tel %>" name="tel"></dd>
		<dt>カテゴリ</dt><dd><span>:&nbsp;</span><%=category %><input type="hidden" value="<%=category %>" name="category"></dd>
	</dl>
	<input type="submit" value="確認" class="button" formaction="/management_list/DeleteCommit">
	<input type="submit" value="戻る" class="button" formmethod="get" formaction="/management_list/ListBL">
</form>

</body>
</html>