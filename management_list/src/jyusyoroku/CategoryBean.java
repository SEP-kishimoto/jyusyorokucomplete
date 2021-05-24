package jyusyoroku;

import java.io.Serializable;

public class CategoryBean implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	/*
	 * private 変数
	 * 倉庫に入れておくべきデータ
	 */
	private String categoryid;
	private String categoryname;
	
	// 引数のないコンストラクタ
	CategoryBean(){};
	
	/*
	 * setter部分
	 */
	public void setCategoryId(String categoryid) {
		this.categoryid = categoryid;
	}
	
	public void setCategoryName(String categoryname) {
		this.categoryname = categoryname;
	}
	
	/*
	 * getter部分
	 */
	public String getCategoryId() {
		return categoryid;
	}
	
	public String getCategoryName() {
		return categoryname;
	}
}
