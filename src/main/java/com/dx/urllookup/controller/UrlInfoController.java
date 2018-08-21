package com.dx.urllookup.controller;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dx.urllookup.domain.SiteInfo;
import com.dx.urllookup.utils.UrlUtils;

@RestController
@RequestMapping("/urlinfo/1")
public class UrlInfoController {

	@Autowired
	UrlUtils urlUtils;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Value("${URL_LOOKUP_SERVICE_BASE_PATH}")
	public  String apiBasePath;
	
	
	/**
	 * API that verifies if specified URL is safe
	 * format GET /urlinfo/1/https%3A%2F%2Fwww.hjaoopoa.top%2Fadmin.php%3Ff%3D1.gif
	 * ensure that the url passed is URL encoded in UTF-8
	 */
	@RequestMapping(value="/**",method=RequestMethod.GET)
	public SiteInfo isUrlSafe(HttpServletRequest request){
		
		String cleanedUrl=urlUtils.normalizeUrl(request.getRequestURI(),apiBasePath);
		//pass cleaned url to query redis
		String result=(String)redisTemplate.opsForValue().get(cleanedUrl);
		result=result==null?"false":result; //will be null when url is not found in redis implies its clean
		return new SiteInfo(cleanedUrl, result);
	}
	
}
