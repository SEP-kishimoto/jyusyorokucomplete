package list;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
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
		// DB接続用変数
				Connection connect = null;
				Statement stmt = null;
				PreparedStatement ps = null;
				ResultSet rs = null;

				// 総件数
				int listCnt = 0;

				// 表取得用クエリ
				String SelectQuery = "";

				// 件数取得用クエリ
				String CntQuery = "";

				// 現在のページ
				String nowPage = "";

				// 検索用文字列
				String SerchName = "";

				// 検索開始件数
				int limitSta = 0;

				// リクエストPageがNullの場合は初期値1を設定、それ以外の時はnowPageにリクエストPageを設定
				if (request.getParameter("Page") == null) {
					nowPage = "1";
				} else {
					nowPage = request.getParameter("Page");
				}

				// limitStaに nowPage - 1を設定
				limitSta = (Integer.parseInt(nowPage) - 1) * 10;

				// 文字コードの変更
				request.setCharacterEncoding("UTF-8");

				// JDBCで接続
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kishimoto?characterEncording=UTF-8&serverTimezone=JST", "root", "");
					stmt = connect.createStatement();

					// 取得対象全件数
					CntQuery = "SELECT COUNT(*) as CNT FROM jyusyoroku";
					// 実施
					rs = stmt.executeQuery(CntQuery);

					// CntQuery実施の際の総件数を設定
					rs.next();
					listCnt = rs.getInt("CNT");


				} catch(Exception e) {
					e.printStackTrace();
				}


				// リクエストSearchnameがNullの場合の処理
				if (request.getParameter("Serchname") == null) {
					try {
						SelectQuery = "SELECT id, name, address, tel, categoryname FROM jyusyoroku"
								+ " JOIN category ON jyusyoroku.categoryid = category.categoryid"
								+ " WHERE jyusyoroku.delete_flg = '0'"
								+ " LIMIT " + limitSta + ", 10";
					} catch(Exception e) {
						e.printStackTrace();
					}

				// リクエストSearchnameがNullでない場合の処理
				} else {
					SerchName = request.getParameter("Serchname");
					//String serchname = new String(SerchName.getBytes("ISO8859_1"), "UTF-8");

					try {
						SelectQuery = "SELECT id, name, address, tel, categoryname FROM jyusyoroku"
								+ " JOIN category ON jyusyoroku.categoryid = category.categoryid"
								+ " WHERE jyusyoroku.delete_flg = 0 AND"
								+ " jyusyoroku.address LIKE '%" + SerchName + "%'"
								+ " LIMIT " + limitSta + ", 10";
					} catch(Exception e) {
						e.printStackTrace();
					}
				}

				try {
					ps = connect.prepareStatement(SelectQuery);
					rs = ps.executeQuery();
				} catch(Exception e) {
					e.printStackTrace();
				}

				request.setAttribute("listCnt", listCnt);
				request.setAttribute("Result", rs);
				request.setAttribute("Page", nowPage);


				// List.jspへの遷移
				String view = "/List.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(view);
				dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);
	}

}
