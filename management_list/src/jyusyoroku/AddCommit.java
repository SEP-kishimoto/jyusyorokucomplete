package jyusyoroku;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddCheck
 */
@WebServlet("/AddCommit")
public class AddCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCommit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 文字コードを宣言
		 */
		request.setCharacterEncoding("UTF-8");
		
		/*
		 * DB接続用変数を宣言
		 */
		Connection connect = null;
		Statement stmt = null;

		/*
		 * 変数を宣言
		 * リクエストされた値を格納するname, address, tel, categoryid
		 * SQL文の変数InsQuery
		 */
		String name = "";
		String address = "";
		String tel = "";
		String categoryid = "";
		String InsQuery = "";

		/*
		 * 変数に値を設定
		 */
		name = request.getParameter("name");
		address = request.getParameter("address");
		tel = request.getParameter("tel");
		categoryid = request.getParameter("categoryid");

		/*
		 * 変数telの文字列から-を取り除く
		 */
		tel = tel.replace("-", "");

		/*
		 * 取得した各値の情報をDBに登録する処理
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kensyudb?characterEncording=UTF-8&serverTimezone=JST", "Portal", "@k7EA2gUY");
			stmt = connect.createStatement();

			/*
			 * InsQueryへクエリを設定
			 */
			InsQuery = "INSERT INTO `jyusyoroku` (`id`, `name`, `address`, `tel`, `categoryid`, `delete_flg`) VALUES (NULL, '"+ name +"', '" + address + "', '" + tel + "', '" + categoryid + "', '0')";

			/*
			 * DBへ登録する
			 */
			stmt.executeUpdate(InsQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * connectを閉じる
		 */
		try {
			connect.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * ListBL.javaへの遷移
		 */
		getServletContext().getRequestDispatcher("/ListBL").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
