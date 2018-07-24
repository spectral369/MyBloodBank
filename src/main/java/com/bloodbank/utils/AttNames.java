package com.bloodbank.utils;

public enum AttNames {
	USERID("userid"),
	LOGGED("logged"),
	RANK("rank"),
	REMEMBERME("rememberme"),
	ISQUPDATED("isqupdated");
	
	
	private final String att;
	AttNames(String att) {
		this.att = att;
	}
	
	public String getAtt() {
		return att;
	}
			

	

}
