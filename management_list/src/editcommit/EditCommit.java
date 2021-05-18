package editcommit;

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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 変数の宣言
		Connection connect = null;
		Statement stmt = null;
		// int i = 0;

		String UpdQuery = "";
		String id = "";
		String name = "";
		String address = "";
		String tel = "";
		String categoryid = "";

		// 文字コードの変更
		request.setCharacterEncoding("UTF-8");

		// 変数に値を入力
		id = request.getParameter("id");
		name = request.getParameter("name");
		address = request.getParameter("address");
		tel = request.getParameter("tel");
		categoryid = request.getParameter("categoryid");

		// telから-を除去する
		tel = tel.replace("-", "");

		// DBに登録する
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kishimoto?characterEncording=UTF-8&serverTimezone=JST", "root", "");
			stmt = connect.createStatement();

			// UpdQueryにクエリを設定
			UpdQuery = "UPDATE jyusyoroku"
					+ " SET name ='" + name + "', address ='" + address + "', tel ='" + tel + "', categoryid ='" + categoryid + "'"
					+ " WHERE id =" + Integer.parseInt(id);

			// 値を更新する
			stmt.executeUpdate(UpdQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ListBL.javaへの遷移
		String view = "/management_list/ListBL";
		response.sendRedirect(view);
	}

}
