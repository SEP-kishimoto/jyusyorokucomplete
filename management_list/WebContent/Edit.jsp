<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jyusyoroku.EditBL" import="jyusyoroku.*" import="java.sql.ResultSet" import="java.util.*"%>
<!-- 文字コード宣言 -->
<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>住所録編集</title>
<style>
<%@ include file="../css/edit.css" %>
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
String category = "";
String errmsg = "";
ResultSet rs = null;
%>

<!-- common.getCategoryAll()で取得した値をrsに格納する -->
<%
Common common = new Common();
ArrayList<CategoryBean> beancategory = new ArrayList<CategoryBean>();
beancategory = Common.getCategoryAll();

%>

<!-- 各設定 -->
<%
errmsg = (String) request.getAttribute("errmsg");

if (errmsg == null) {
	id = (String) request.getParameter("id");
	name = (String) request.getParameter("name");
	address = (String) request.getParameter("address");
	tel = (String) request.getParameter("tel");
	categoryid = (String) request.getParameter("categoryid");
	category = (String) request.getParameter("categoryname");
} else {
	id = (String) request.getAttribute("id");
	name = (String) request.getAttribute("name");
	address = (String) request.getAttribute("address");
	tel = (String) request.getAttribute("tel");
	categoryid = (String) request.getAttribute("categoryid");
	category = common.getCategoryName(categoryid);
}
%>

<form method="post">
	<input type="hidden" name="id" value="<%=id %>">
	<dl>
		<dt>名前＊</dt><dd><span>:&nbsp;</span><input type="text" name="name" value="<%=name %>"></dd>
		<dt>住所＊</dt><dd><span>:&nbsp;</span><input type="text" class="addresswidth" name="address" value="<%=address %>"></dd>
		<%if (tel.equals("")) { %>
		<dt>電話番号</dt><dd><span>:&nbsp;</span><input type="text" name="tel"></dd>
		<%} else if (!(tel.matches("\\d{3}-\\d{4}-\\d{4}"))) { %>
		<dt>電話番号</dt><dd><span>:&nbsp;</span><input type="text" name="tel" value="<%=tel%>"></dd>
		<%} else {%>
		<%tel = tel.replace("-", ""); %>
		<dt>電話番号</dt><dd><span>:&nbsp;</span><input type="text" name="tel" value="<%=tel.substring(0, 3) + "-" + tel.substring(3, 7) + "-" + tel.substring(7, 11)%>"></dd>
		<%} %>
		<dt>カテゴリ</dt><dd><span>:</span>
		<select name="categoryid" >
			<%for (int i = 0; i < beancategory.size(); i++) { %>
				<%if (category.equals(beancategory.get(i).getCategoryName())) {%>
					<option value="<%=beancategory.get(i).getCategoryId() %>" selected><%=beancategory.get(i).getCategoryName() %></option>
				<%} else { %>
					<option value="<%=beancategory.get(i).getCategoryId() %>"><%=beancategory.get(i).getCategoryName() %></option>
				<%} %>
			<%} %>
		</select>
		</dd>
	</dl>
	<%if (errmsg == null) { %>
		<p></p>
	<%} else { %>
		<p><%=errmsg %></p>
	<%} %>
	<!-- <input type="hidden" name="categoryname" value="<%=category %>"> -->
	<input type="submit" value="確認" class="button" formaction="/management_list/EditBL">
	<input type="submit" value="戻る" class="button" formmethod="get" formaction="/management_list/ListBL">
</form>
</body>
</html>