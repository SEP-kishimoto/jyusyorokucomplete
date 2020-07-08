package deletecommit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteCommit
 */
@WebServlet("/DeleteCommit")
public class DeleteCommit extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCommit() {
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

		String UpdQuery = "";
		String id = "";

		// 値を設定する
		id = request.getParameter("id");


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kishimoto?characterEncording=UTF-8&serverTimezone=JST", "root", "");
			stmt = connect.createStatement();

			// DBにリクエスト内容を登録
			UpdQuery = "DELETE FROM jyusyoroku"
					+ " WHERE id=" + Integer.parseInt(id);

			// 内容を更新させる
			stmt.executeUpdate(UpdQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ListBL.javaへの遷移
		String view = "/management_list/ListBL";
		response.sendRedirect(view);

	}

}
