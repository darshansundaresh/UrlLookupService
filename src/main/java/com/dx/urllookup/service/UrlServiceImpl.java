package com.dx.urllookup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dx.urllookup.domain.SiteInfo;
import com.dx.urllookup.utils.UrlUtils;

@Service
public class UrlServiceImpl implements UrlService{

	@Autowired
	UrlUtils urlUtils;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Value("${URL_LOOKUP_SERVICE_BASE_PATH}")
	public  String verifyApiBasePath;
	
	@Value("${URL_ADD_SERVICE_BASE_PATH}")
	public  String addApiBasePath;
	
	@Override
	public SiteInfo verifySite(String url) {
		String cleanedUrl=urlUtils.normalizeUrl(url,verifyApiBasePath);
		//pass cleaned url to query redis
		String result=(String)redisTemplate.opsForValue().get(cleanedUrl);
		result=result==null?"false":result; //will be null when url is not found in redis implies its clean
		return new SiteInfo(cleanedUrl, result);
	}

	@Override
	public SiteInfo addMaliciousSite(String url) {
		String cleanedUrl=urlUtils.normalizeUrl(url,addApiBasePath);
		//pass only cleaned url to redis as key
		SiteInfo site=new SiteInfo(cleanedUrl, "true");
		redisTemplate.opsForValue().set(cleanedUrl,true);
		return site;
	}

}
