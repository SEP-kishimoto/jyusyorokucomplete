package jyusyoroku;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddBL
 */
@WebServlet("/AddBL")
public class AddBL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 文字コードの設定
		 */
		request.setCharacterEncoding("UTF-8");
		
		/*
		 * 変数の宣言
		 * name, address, tel, categoryid, errmsg
		 */
		String name = "";
		String address = "";
		String tel = "";
		String categoryid = "";
		String errmsg = "";

		/*
		 * 値の設定
		 */
		name = request.getParameter("name");
		address = request.getParameter("address");
		tel = request.getParameter("tel");
		categoryid = request.getParameter("categoryid");

		/*
		 * エラーメッセージを設定
		 */
		Common common = new Common();
		errmsg = common.getErr(name, address, tel);

		/*
		 * setAttribute
		 * 遷移先にリクエストを渡す処理
		 */
		request.setAttribute("name", name);
		request.setAttribute("address", address);
		request.setAttribute("tel", tel);
		request.setAttribute("categoryid", categoryid);
		request.setAttribute("errmsg", errmsg);

		/*
		 * errmsgがブランクの場合はAddCheck.jspに遷移
		 * それ以外はAdd.jspへの遷移
		 */
		if (errmsg == "") {
			getServletContext().getRequestDispatcher("/AddCheck.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/Add.jsp").forward(request, response);
		}		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
