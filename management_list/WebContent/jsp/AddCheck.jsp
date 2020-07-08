<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
import="common.Common"
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
<style>
<%@ include file="../css/addcheck.css" %>
</style>
</head>

<body>

<%
request.setCharacterEncoding("UTF-8");
%>

<!-- 変数の宣言 -->
<%
String name = "";
String address = "";
String tel = "";
String categoryid = "";
String categoryname = "";
%>

<!-- 各変数に設定 -->
<%
name = (String) request.getAttribute("name");
address = (String) request.getAttribute("address");
tel = (String) request.getAttribute("tel");
categoryid = (String) request.getAttribute("categoryid");

Common common = new Common();
categoryname = common.getCategoryName(categoryid);

%>

<form method="post">
	<dl>
		<dt>名前*</dt><dd><span>:&nbsp;</span><%=name %> <input type="hidden" value="<%=name %>" name="name"></dd>
		<dt>住所*</dt><dd><span>:&nbsp;</span><%=address %> <input type="hidden" value="<%=address %>" name="address"></dd>
		<dt>電話番号</dt><dd><span>:&nbsp;</span><%=tel %> <input type="hidden" value="<%=tel %>"name="tel"></dd>
	</dl>
	<input type="hidden" value="<%=categoryid %>" name="categoryid">
	<input type="submit" value="登録" class="button" formaction="/management_list/AddCommit">
	<input type="submit" value="戻る" class="button" formmethod="get" formaction="/management_list/ListBL">
</form>

</body>
</html>