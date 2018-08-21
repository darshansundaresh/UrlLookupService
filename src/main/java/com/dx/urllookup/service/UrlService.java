package com.dx.urllookup.service;

import com.dx.urllookup.domain.SiteInfo;

public interface UrlService {
	
	public SiteInfo verifySite(String url);
	public SiteInfo addMaliciousSite(String url);
}
