<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jyusyoroku.EditBL" import="jyusyoroku.Common" %>
<!-- 文字コード宣言 -->
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住所録編集確認</title>
<style>
<%@ include file="../css/editcheck.css" %>
</style>
</head>
<body>

<!-- 変数の宣言 -->
<%
String id = "";
String name = "";
String address = "";
String tel = "";
String categoryid = "";
String categoryname = "";
%>

<%
id = (String) request.getAttribute("id");
name = (String) request.getAttribute("name");
address = (String) request.getAttribute("address");
tel = (String) request.getAttribute("tel");
categoryid = (String) request.getAttribute("categoryid");
%>

<!-- common.getCategoryname()で取得した値を設定 -->
<%
Common common = new Common();
categoryname = common.getCategoryName(categoryid);
%>

<form method="post">
	<input type="hidden" value="<%=id %>" name="id">
	<dl>
		<dt>名前*</dt><dd><span>:&nbsp;</span><%=name %><input type="hidden" value="<%=name %>" name="name"></dd>
		<dt>住所*</dt><dd><span>:&nbsp;</span><%=address %> <input type="hidden" value="<%=address %>" name="address"></dd>
		<dt>電話番号</dt><dd><span>:&nbsp;</span><%=tel %> <input type="hidden" value="<%=tel %>" name="tel"></dd>
	</dl>
	<input type="hidden" value="<%=categoryid %>" name="categoryid">
	<input type="submit" value="編集" class="button" formaction="/management_list/EditCommit">
	<input type="submit" value="戻る" class="button" formmethod="get" formaction="/management_list/ListBL">
</form>

</body>
</html>