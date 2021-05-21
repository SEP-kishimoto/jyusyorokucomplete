package jyusyoroku;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class list
 */
@WebServlet("/ListBL")
public class ListBL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListBL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 文字コードの変更
		 */
		request.setCharacterEncoding("UTF-8");
		
		/*
		 * DB接続用変数
		 */
		Connection connect = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		/*
		 * DB宣言
		 * Connection, Statementの設定
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kensyudb?characterEncording=UTF-8&serverTimezone=JST", "Portal", "@k7EA2gUY");
			stmt = connect.createStatement();
		} catch(Exception e) {
			e.printStackTrace();
		}

		/*
		 * 総件数, 表取得用クエリ, 件数取得用クエリ, 現在のページ, 検索開始件数
		 * DBで取得した結果を格納するbeanList
		 */
		int listCnt = 0;
		String SelectQuery = "";
		String CntQuery = "";
		String nowPage = "";
		int limitSta = 0;
		ArrayList<ListBean> beanList = new ArrayList<ListBean>();
		
		/*
		 * 検索変数 Serch
		 * 住所の検索文字の取得
		 */
		String Serch = request.getParameter("Serch");
		if (request.getParameter("Serch") == null) {
			Serch = (String) request.getAttribute("Serch");
		}
			
		/*
		 * PageがNullの場合初期値として1を設定
		 * その他、nowPageにrequest.getParmeter("Page")を設定
		 */
		if (request.getParameter("Page") == null) {
			nowPage = "1";
		} else {
			nowPage = request.getParameter("Page");
		}

		/*
		 * limitStaに現在のページ(nowPage-1)で表示するページの開始地点を取得
		 */
		limitSta = (Integer.parseInt(nowPage) - 1) * 10;

		/*
		 * 全件数をlistCntに取得
		 * Serchの有無でCntQueryで取得するDB情報を分岐
		 */
		try {
			if (Serch != null && Serch != "") {
				/*
				 * Serchがある場合
				 */
				CntQuery = "SELECT COUNT(*) as CNT FROM jyusyoroku WHERE address LIKE '%" + Serch + "%'";
				rs = stmt.executeQuery(CntQuery);
				rs.next();
				listCnt = rs.getInt("CNT");
			} else {
				/*
				 * Serchがない場合
				 */
				CntQuery = "SELECT COUNT(*) as CNT FROM jyusyoroku";
				rs = stmt.executeQuery(CntQuery);
				rs.next();
				listCnt = rs.getInt("CNT");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		/*
		 * リクエストSerchがNullの場合の処理
		 */
		try {
			if (Serch == null) {
				SelectQuery = "SELECT id, name, address, tel, categoryname, delete_flg FROM jyusyoroku"
						+ " JOIN category ON jyusyoroku.categoryid = category.categoryid"
						+ " WHERE jyusyoroku.delete_flg = '0'"
						+ " LIMIT " + limitSta + ", 10";
			} else {
				SelectQuery = "SELECT id, name, address, tel, categoryname, delete_flg FROM jyusyoroku"
						+ " JOIN category ON jyusyoroku.categoryid = category.categoryid"
						+ " WHERE jyusyoroku.delete_flg = 0 AND"
						+ " jyusyoroku.address LIKE '%" + Serch + "%'"
						+ " LIMIT " + limitSta + ", 10";
			}
			
			/*
			 * beanListに取得したDB情報を格納
			 */
			ps = connect.prepareStatement(SelectQuery);
			rs = ps.executeQuery();
			while(rs.next()) {
				ListBean bean = new ListBean();
				bean.setId(rs.getInt("id"));
				bean.setName(rs.getString("name"));
				bean.setAddress(rs.getString("address"));
				bean.setTel(rs.getString("tel"));
				bean.setCategoryName(rs.getString("categoryname"));
				bean.setDeleteFlg(rs.getString("delete_flg"));
						
				beanList.add(bean);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		/*
		 * setAttribute
		 * 遷移先にリクエストを渡す処理
		 * Serchのみnullの場合、""にする分岐処理
		 */
		request.setAttribute("listCnt", listCnt);
		request.setAttribute("beanList", beanList);
		request.setAttribute("Result", rs);
		request.setAttribute("Page", nowPage);
		if (Serch == null) {
			Serch = "";
		}
		request.setAttribute("Serch", Serch);

		/*
		 * List.jspへ遷移
		 */
		getServletContext().getRequestDispatcher("/List.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
