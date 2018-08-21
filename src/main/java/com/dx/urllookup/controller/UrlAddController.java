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
@RequestMapping("/urladd/1")
public class UrlAddController {

	@Value("${URL_ADD_SERVICE_BASE_PATH}")
	public  String apiBasePath;
	
	@Autowired
	UrlUtils urlUtils;
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	
	/**
	 * Saves the URL into the Malicious list in redis
	 * format POST /urladd/1/https%3A%2F%2Fwww.hjaoopoa.top%2Fadmin.php%3Ff%3D1.gif
	 * ensure that the url being added is URL encoded in UTF-8
	 */
	@RequestMapping(value="/**",method=RequestMethod.POST)
	public SiteInfo saveMaliciousUrl(HttpServletRequest request){
		String cleanedUrl=urlUtils.normalizeUrl(request.getRequestURI(),apiBasePath);
		//pass only cleaned url to redis as key
		SiteInfo site=new SiteInfo(cleanedUrl, "true");
		redisTemplate.opsForValue().set(cleanedUrl,true);
		return site;
	}
}
