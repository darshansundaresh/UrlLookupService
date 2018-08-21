package com.dx.urllookup.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dx.urllookup.domain.SiteInfo;
import com.dx.urllookup.service.UrlService;


@RestController
@RequestMapping("/urlinfo/1")
public class UrlInfoController {

	@Autowired
	UrlService urlService;
	
	
	/**
	 * API that verifies if specified URL is safe
	 * format GET /urlinfo/1/https%3A%2F%2Fwww.hjaoopoa.top%2Fadmin.php%3Ff%3D1.gif
	 * ensure that the url passed is URL encoded in UTF-8
	 */
	@RequestMapping(value="/**",method=RequestMethod.GET)
	public SiteInfo isUrlSafe(HttpServletRequest request){
		return urlService.verifySite(request.getRequestURI());
		
	}
	
}
