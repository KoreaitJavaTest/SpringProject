package com.Team.Vo;

import java.util.Date;

public class ClientVo {
	private int client_idx;
	private int client_level;
	private String client_id;
	private String client_password;
	private String client_gender;
	private String client_addr_head;
	private String client_addr_end;
	private String client_phone;
	private String client_email;
	private String client_emailcode;
	private String client_emailcheck;
	private String client_point;
	private Date client_rdate;
	
	public ClientVo() {}
	
	
	public void setClientVo(String client_id, String client_password, String client_gender, String client_addr_head,
			String client_addr_end, String client_phone, String client_email, String client_emailcode) {
		this.client_id = client_id;
		this.client_password = client_password;
		this.client_gender = client_gender;
		this.client_addr_head = client_addr_head;
		this.client_addr_end = client_addr_end;
		this.client_phone = client_phone;
		this.client_email = client_email;
		this.client_emailcode = client_emailcode;
	}


	public void setClientVo(String client_id ,String client_password) {
		this.client_id = client_id;
		this.client_password = client_password;
	}

	public int getClient_idx() {
		return client_idx;
	}
	public void setClient_idx(int client_idx) {
		this.client_idx = client_idx;
	}
	public int getClient_level() {
		return client_level;
	}
	public void setClient_level(int client_level) {
		this.client_level = client_level;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_password() {
		return client_password;
	}
	public void setClient_password(String client_password) {
		this.client_password = client_password;
	}
	public String getClient_gender() {
		return client_gender;
	}
	public void setClient_gender(String client_gender) {
		this.client_gender = client_gender;
	}
	public String getClient_addr_head() {
		return client_addr_head;
	}
	public void setClient_addr_head(String client_addr_head) {
		this.client_addr_head = client_addr_head;
	}
	public String getClient_addr_end() {
		return client_addr_end;
	}
	public void setClient_addr_end(String client_addr_end) {
		this.client_addr_end = client_addr_end;
	}
	public String getClient_phone() {
		return client_phone;
	}
	public void setClient_phone(String client_phone) {
		this.client_phone = client_phone;
	}
	public String getClient_email() {
		return client_email;
	}
	public void setClient_email(String client_email) {
		this.client_email = client_email;
	}
	public String getClient_emailcode() {
		return client_emailcode;
	}
	public void setClient_emailcode(String client_emailcode) {
		this.client_emailcode = client_emailcode;
	}
	public String getClient_emailcheck() {
		return client_emailcheck;
	}
	public void setClient_emailcheck(String client_emailcheck) {
		this.client_emailcheck = client_emailcheck;
	}
	public String getClient_point() {
		return client_point;
	}
	public void setClient_point(String client_point) {
		this.client_point = client_point;
	}
	public Date getClient_rdate() {
		return client_rdate;
	}
	public void setClient_rdate(Date client_rdate) {
		this.client_rdate = client_rdate;
	}
	
	@Override
	public String toString() {
		return "ClientVo [client_idx=" + client_idx + ", client_level=" + client_level + ", client_id=" + client_id
				+ ", client_password=" + client_password + ", client_gender=" + client_gender + ", client_addr_head="
				+ client_addr_head + ", client_addr_end=" + client_addr_end + ", client_phone=" + client_phone
				+ ", client_email=" + client_email + ", client_emailcode=" + client_emailcode + ", client_emailcheck="
				+ client_emailcheck + ", client_point=" + client_point + ", client_rdate=" + client_rdate + "]";
	}
	
	
}
