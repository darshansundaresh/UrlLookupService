package com.dx.urllookup.utils;

import java.net.URI;
import java.net.URLDecoder;

import org.springframework.stereotype.Component;


@Component
public class UrlUtils {
	

	/**
	 * Normalizes the URL's to store in redis
	 * @param dirtyUrl  
	 * @param apiBasePath
	 * @return normalized URL without port number, scheme, protocol etc
	 */
	public  String normalizeUrl(String dirtyUrl,String apiBasePath){
		try{
			if(dirtyUrl.length()>0){
				dirtyUrl=dirtyUrl.replace(apiBasePath, "").replace("www.", "").replace("WWW.", "");
				String fullUrl=URLDecoder.decode(dirtyUrl,"UTF-8");
				
				URI url=URI.create(fullUrl);
				String normalUrl=url.getHost()!=null?url.getHost():"";
				normalUrl+=url.getPath()!=null?url.getPath():"";
				normalUrl+=url.getQuery()!=null?"?"+url.getQuery():"";
				
				return normalUrl;
			}else{
				return "";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
}
