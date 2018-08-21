package com.dx.urllookup.domain;

public class SiteInfo {
	private String fullUrl;
	private String isMalicious;
	
	public SiteInfo(String fullUrl,String isMalicious) {
		this.fullUrl=fullUrl;
		this.isMalicious=isMalicious;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getIsMalicious() {
		return isMalicious;
	}

	public void setIsMalicious(String isMalicious) {
		this.isMalicious = isMalicious;
	}
}
