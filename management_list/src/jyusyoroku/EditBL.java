package jyusyoroku;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditBL
 */
@WebServlet("/EditBL")
public class EditBL extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditBL() {
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
		 * 変数を宣言
		 * 変数にrequestした値を入れる
		 */
		String id = request.getParameter("id");;
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String categoryid = request.getParameter("categoryid");
		String errmsg = "";

		/*
		 * エラーメッセージを設定
		 */
		Common common = new Common();
		errmsg = common.getErr(name, address, tel);

		/*
		 * 遷移先へのリクエスト情報を作成
		 */
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("address", address);
		request.setAttribute("tel", tel);
		request.setAttribute("categoryid", categoryid);
		request.setAttribute("errmsg", errmsg);

		/*
		 * errmsgがブランクの場合はEditCheck.jspに遷移
		 * それ以外はEdit.jspへの遷移
		 */
		if (errmsg == "") {
			getServletContext().getRequestDispatcher("/EditCheck.jsp").forward(request, response);
		} else {
			getServletContext().getRequestDispatcher("/Edit.jsp").forward(request, response);
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
