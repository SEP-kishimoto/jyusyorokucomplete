package addcommit;

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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 変数を宣言
		Connection connect = null;
		Statement stmt = null;

		String InsQuery = "";
		String name = "";
		String address = "";
		String tel = "";
		String categoryid = "";

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


		// 変数に設定
		name = request.getParameter("name");
		address = request.getParameter("address");
		tel = request.getParameter("tel");
		categoryid = request.getParameter("categoryid");

		// telの文字列から-を取り除く
		tel = tel.replace("-", "");

		// DBにぶっ込む場所
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kishimoto?characterEncording=UTF-8&serverTimezone=JST", "root", "");
			stmt = connect.createStatement();

			// InsQueryへクエリを設定する
			InsQuery = "INSERT INTO `jyusyoroku` (`id`, `name`, `address`, `tel`, `categoryid`, `delete_flg`) VALUES (NULL, '"+ name +"', '" + address + "', '" + tel + "', '" + categoryid + "', '0')";

			// DBへ登録する
			stmt.executeUpdate(InsQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ListBL.javaへの遷移
		String view = "/management_list/ListBL";
		response.sendRedirect(view);
	}

}
