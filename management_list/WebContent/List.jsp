<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="jyusyoroku.ListBL" import="java.sql.*" import="java.net.URLEncoder"
	import="java.text.NumberFormat" import="java.util.*" %>

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

<!-- 変数の宣言 -->
<%
ResultSet rs = null;
int listCnt = 0;
String nowPage = null;
int maxPage = 0;
ArrayList<jyusyoroku.ListBean> beanList = new ArrayList<jyusyoroku.ListBean>();
String Serch = (String) request.getAttribute("Serch");

%>

<!-- それぞれの処理 -->
<%
nowPage = (String) request.getAttribute("Page");
listCnt = (int) request.getAttribute("listCnt");

int now = Integer.parseInt(nowPage);
maxPage = listCnt / 10;
if (listCnt % 10 != 0) {
	maxPage = maxPage + 1;
}

int start = 0;
int end = 0;

if (listCnt == 0) {
	maxPage = 1;
}

beanList = (ArrayList<jyusyoroku.ListBean>) request.getAttribute("beanList");
rs = (ResultSet) request.getAttribute("Result");
%>

<!-- 表示画面のコード -->

<h1>住所録管理システム：住所録一覧</h1>
<input id="add" type="button" onclick="location.href='./Add.jsp'" value="新規登録">

<!-- 検索用テキストボックス -->

<form method="get" class="serchbox">
	<table style="float: right" class="serchtable">
		<tr>
			<td><label for="jyusyo">住所：</label></td>
			<td><input id="jyusyo" type="text" name="Serch" class="serchtext"><br></td>
		</tr>
		<tr>
			<td></td>
			<td><input type="submit" value="検索" class="serch-button"></td>
		</tr>
	</table>
</form>

<!-- ページング処理 -->
<form class="page-up" action="ListBL" method="get">
		<ul>
			<li>
				<%if (now == 1) {%>
			<a><%="<<"%></a>
			<%} else { %>
			<a href="ListBL?Page=1&Serch=<%=Serch %>"><%="<<"%></a>
			<%}%>
			</li>
			<li>
				<%if (now == 1) {%> <a><%="<"%></a>
				<%} else {%>
				<a href="ListBL?Page=<%=now - 1%>&Serch=<%=Serch %>"><%="<"%></a>
				<%}%>
			</li>
			<%
				if (maxPage <= 5) {
				start = 1;
				end = maxPage;
			} else if (now == maxPage || now == maxPage - 1) {
				start = maxPage - 4;
				end = maxPage;
			} else if (now >= 3) {
				start = now - 2;
				end = now + 2;
			} else {
				start = 1;
				end = 5;
			}
			for (int i = start; i <= end; i++) {
			%>
			<li>
				<%if (now == i) {%>
				<a><%=i%></a>
				<%} else { %>
				<a href="ListBL?Page=<%=i%>&Serch=<%=Serch %>"><%=i%></a>
				<%}%>
			</li>
			<%if (maxPage != i) {%>
			<li>|</li>
			<%} %>
			<%}%>
			<li>
				<%if (now == maxPage) {%> <a><%=">"%></a>
				<%} else {%>
				<a href="ListBL?Page=<%=now + 1%>&Serch=<%=Serch %>"><%=">"%></a>
				<%}%>
			</li>
			<li>
				<%if (now == maxPage) {%> <a><%=">>"%></a>
				<%} else {%>
				<a href="ListBL?Page=<%=maxPage%>&Serch=<%=Serch %>"><%=">>"%></a>
				<%}%>
			</li>
		</ul>
</form>

<!-- 取得したDB情報を表示するテーブル部分 -->
<table class="listcss">
	<tr>
		<th width="30px" >No.</th>
		<th width="150px" >名前</th>
		<th width="380px" >住所</th>
		<th width="200px">電話番号</th>
		<th width="80px" ><font color="#c71585f">カテゴリ</font></th>
		<th></th>
	</tr>
<% for (int s = 0; s < beanList.size(); s++) { %>
<% if(beanList.get(s).getDeleteFlg().equals("0")) {%>
	<tr>
		<td><p><%=beanList.get(s).getId() %></p></td>
    	<td><p><%=beanList.get(s).getName() %></p></td>
    	<td><p class="threereader"><%=beanList.get(s).getAddress() %><span class="tooltip"><%=beanList.get(s).getAddress() %></span></p></td>
    	<%if (beanList.get(s).getTel().equals("")) { %>
    		<td>&nbsp;</td>
    	<%} else { %>
    		<td><p><%=beanList.get(s).getTel().substring(0, 3) + "-" + beanList.get(s).getTel().substring(3, 7) + "-" + beanList.get(s).getTel().substring(7, 11)%></p></td>
    	<%} %>
    	<td><p><font color="#c71585f"><%=beanList.get(s).getCategoryName() %></font></p></td>
    	<td>
    	<form method="post">
    	<input type="hidden" name="id" value="<%=beanList.get(s).getId() %>">
    	<input type="hidden" name="name" value="<%=beanList.get(s).getName() %>">
    	<input type="hidden" name="address" value="<%=beanList.get(s).getAddress() %>">
    	<%if (beanList.get(s).getTel().equals("")) { %>
    		<input type="hidden" name="tel" value="<%=beanList.get(s).getTel() %>">
    	<%} else { %>
    		<input type="hidden" name="tel" value="<%=beanList.get(s).getTel().substring(0, 3) + "-" + beanList.get(s).getTel().substring(3, 7) + "-" + beanList.get(s).getTel().substring(7, 11) %>">
    	<%} %>
    	<input type="hidden" name="categoryname" value="<%=beanList.get(s).getCategoryName() %>">
    	<input type="submit" class="table-button" formaction="./Edit.jsp" value="編集"><input type="submit" class="table-button" formaction="./Delete.jsp" value="削除"></form></td>
	</tr>
<% } %>
<% } %>
</table>

<!-- ページング処理 -->
<form class="page-down" action="ListBL" method="get">
		<ul>
			<li>
				<%if (now == 1) {%>
			<a><%="<<"%></a>
			<%} else { %>
			<a href="ListBL?Page=1&Serch=<%=Serch %>"><%="<<"%></a>
			<%}%>
			</li>
			<li>
				<%if (now == 1) {%> <a><%="<"%></a>
				<%} else {%>
				<a href="ListBL?Page=<%=now - 1%>&Serch=<%=Serch %>"><%="<"%></a>
				<%}%>
			</li>
			<%
				if (maxPage <= 5) {
				start = 1;
				end = maxPage;
			} else if (now == maxPage || now == maxPage - 1) {
				start = maxPage - 4;
				end = maxPage;
			} else if (now >= 3) {
				start = now - 2;
				end = now + 2;
			} else {
				start = 1;
				end = 5;
			}
			for (int i = start; i <= end; i++) {
			%>
			<li>
				<%if (now == i) {%>
				<a><%=i%></a>
				<%} else { %>
				<a href="ListBL?Page=<%=i%>&Serch=<%=Serch %>"><%=i%></a>
				<%}%>
			</li>
			<%if (maxPage != i) {%>
			<li>|</li>
			<%} %>
			<%}%>
			<li>
				<%if (now == maxPage) {%> <a><%=">"%></a>
				<%} else {%>
				<a href="ListBL?Page=<%=now + 1%>&Serch=<%=Serch %>"><%=">"%></a>
				<%}%>
			</li>
			<li>
				<%if (now == maxPage) {%> <a><%=">>"%></a>
				<%} else {%>
				<a href="ListBL?Page=<%=maxPage%>&Serch=<%=Serch %>"><%=">>"%></a>
				<%}%>
			</li>
		</ul>
</form>
<!-- 新規登録ボタン -->
<input id="add" type="button" onclick="location.href='./jsp/Add.jsp'" value="新規登録" class="button-bottom">

</body>
</html>