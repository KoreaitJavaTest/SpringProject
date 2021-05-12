package com.Team.Vo;

import java.util.Date;

public class IpVo {
	private String ip;
	private Date indate;
	
	public IpVo() {
		// TODO Auto-generated constructor stub
	}
	
	public void setIpVo(String ip,Date indate) {
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
		return "IpVo [ip=" + ip + ", indate=" + indate + "]";
	}
	
	
	
}
