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
 * Servlet implementation class EditCommit
 */
@WebServlet("/EditCommit")
public class EditCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCommit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 文字コードの宣言
		 */
		request.setCharacterEncoding("UTF-8");

		/*
		 * 変数の宣言
		 */
		Connection connect = null;
		Statement stmt = null;

		String UpdQuery = "";
		String id = "";
		String name = "";
		String address = "";
		String tel = "";
		String categoryid = "";

		/*
		 * 変数に値を設定
		 */
		id = request.getParameter("id");
		name = request.getParameter("name");
		address = request.getParameter("address");
		tel = request.getParameter("tel");
		categoryid = request.getParameter("categoryid");

		/*
		 * telから-を除去する
		 */
		tel = tel.replace("-", "");

		/*
		 * DBに登録する
		 */
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kensyudb?characterEncording=UTF-8&serverTimezone=JST", "Portal", "@k7EA2gUY");
			stmt = connect.createStatement();

			/*
			 * UpdQueryへクエリを設定
			 */
			UpdQuery = "UPDATE jyusyoroku"
					+ " SET name ='" + name + "', address ='" + address + "', tel ='" + tel + "', categoryid ='" + categoryid + "'"
					+ " WHERE id =" + Integer.parseInt(id);

			/*
			 * 編集した値を更新する
			 */
			stmt.executeUpdate(UpdQuery);

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
		 * ListBLへの遷移
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
