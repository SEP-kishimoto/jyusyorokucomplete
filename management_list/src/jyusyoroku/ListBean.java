package jyusyoroku;

import java.io.Serializable;

public class ListBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/*
	 * private 変数
	 * 倉庫に入れておくべきデータ
	 */
	private int id;
	private String name;
	private String address;
	private String tel;
	private String categoryid;
	private String delete_flg;
	
	// 引数のないコンストラクタ
	ListBean(){};
	
	/*
	 * setter部分
	 */
	public void setId(int id) {
		this.id= id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	public void setCategoryName(String categoryid) {
		this.categoryid = categoryid;
	}
	
	public void setDeleteFlg(String delete_flg) {
		this.delete_flg = delete_flg;
	}
	
	/*
	 * getter部分
	 */
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getTel() {
		return tel;
	}
	
	public String getCategoryName() {
		return categoryid;
	}
	
	public String getDeleteFlg() {
		return delete_flg;
	}
	
	
	
	
	
}
