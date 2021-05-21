package jyusyoroku;


import java.io.UnsupportedEncodingException;
// import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class Common {

	/**
	 * エラーメッセージ用のメソッドです。引数を処理して戻り値を返します。
	 * @param 名前 name(String), 住所 address(String), 電話番号 tel(String)
	 * @return 処理結果データ returnVal
	 * @see java.jyusyoroku.Common
	 */
	public String getErr(String name, String address, String tel) {

		// 定数宣言
		final String ERRMSG_NAME01 = "名前は全角20文字以内で入力してください";
		final String ERRMSG_NAME02 = "名前は必須項目です";
		final String ERRMSG_ADDRESS01 = "住所は全角40文字以内で入力してください";
		final String ERRMSG_ADDRESS02 = "住所は必須項目です";
		final String ERRMSG_TEL01 = "電話番号は「000-0000-0000」の形式で入力してください";

		// 戻り値の変数
		String returnVal = "";

		if (Bytes(name) > 40) {
			returnVal += ERRMSG_NAME01 + "<BR> ";
		} else if (Bytes(name) == 0) {
			returnVal += ERRMSG_NAME02 + "<BR> ";
		}

		if (Bytes(address) > 80) {
			returnVal += ERRMSG_ADDRESS01 + "<BR> ";
		} else if (Bytes(address) == 0) {
			returnVal += ERRMSG_ADDRESS02 + "<BR> ";
		}

		if (Bytes(tel) != 0 && !(tel.matches("\\d{3}-\\d{4}-\\d{4}"))) {//"^\\d{3}-\\d{4}-\\d{4}$" "^0\\d{2,3}-\\d{1,4}-\\d{4}$"
			returnVal += ERRMSG_TEL01 + "<BR> ";
		}



		return returnVal;
	}

	/**
	 * 文字列のバイト数を取り出すためのメソッドです、引数を処理して戻り値を返します。
	 * @param 文字列 value(String)
	 * @return 処理結果データ bytes
	 * @see java.jyusyoroku.Common
	 * */

	public int Bytes(String value) {
		int bytes = 0;

		if (value == null) value = "";

		try {
			bytes = value.getBytes("SJIS").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bytes;

	}

	/**
	 * カテゴリのデータを取得するためのメソッド
	 * @return 処理結果データ rs(ResultSet)
	 * @see java.jyusyoroku.Common
	 * */

	public ResultSet getCategoryAll() {

		// DB接続用変数
		Connection connect = null;
		Statement stmt = null;
		ResultSet rs = null;

		// 取得用クエリ
		String getQuery = "";

		// JDBCで接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kensyudb?characterEncording=UTF-8&serverTimezone=JST", "Portal", "@k7EA2gUY");
			stmt = connect.createStatement();

			getQuery = "SELECT categoryid, categoryname FROM category"
					+ " ORDER BY categoryid ASC";

			// 実施
			rs = stmt.executeQuery(getQuery);

		} catch(Exception e) {
			e.printStackTrace();
		}


		return rs;
	}

	public String getCategoryName(String id) {

		// DB接続用変数
		Connection connect = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// 取得用クエリ
		String getQuery = "";

		// カテゴリ名
		String categoryname = "";

		// JDBCで接続
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/kensyudb?characterEncording=UTF-8&serverTimezone=JST", "Portal", "@k7EA2gUY");


			getQuery = "SELECT categoryid, categoryname FROM category WHERE categoryid = ?";

			ps = connect.prepareStatement(getQuery);
			ps.setString(1, id);

			// 実施
			rs = ps.executeQuery();
			rs.next();
			categoryname = rs.getString("categoryname");

		} catch(Exception e) {
			e.printStackTrace();
		}


		return categoryname;
	}

}
