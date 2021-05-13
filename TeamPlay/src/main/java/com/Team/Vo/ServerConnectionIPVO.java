package com.Team.Vo;

import java.util.Date;

public class ServerConnectionIPVO {
	String ip;
	Date indate;
	
	
	
	public void SetServerConnectionIPVO(String ip, Date indate) {
		this.ip = ip;
		this.indate = indate;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	@Override
	public String toString() {
		return "ServerConnectionIPVO [ip=" + ip + ", indate=" + indate + "]";
	}
	
	
	
}

