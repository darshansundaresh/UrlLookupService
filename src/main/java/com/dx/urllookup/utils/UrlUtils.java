package com.dx.urllookup.utils;

import java.net.URI;
import java.net.URLDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.dx.urllookup.exceptions.InvalidUrlException;


@Component
public class UrlUtils {
	private Logger log = LoggerFactory.getLogger(UrlUtils.class);

	/**
	 * Normalizes the URL's to store in redis
	 * @param dirtyUrl  
	 * @param apiBasePath
	 * @return normalized URL without port number, scheme, protocol etc
	 */
	public  String normalizeUrl(String dirtyUrl,String apiBasePath){
		try{
			
			dirtyUrl=dirtyUrl.replace(apiBasePath, "").replace("www.", "").replace("WWW.", "");
			if(dirtyUrl.length()>0){
				String fullUrl=URLDecoder.decode(dirtyUrl,"UTF-8");
				
				URI url=URI.create(fullUrl);
				String normalUrl=url.getHost()!=null?url.getHost():"";
				normalUrl+=url.getPath()!=null?url.getPath():"";
				normalUrl+=url.getQuery()!=null?"?"+url.getQuery():"";
				
				return normalUrl;
			}else{
				throw new InvalidUrlException();
			}
		}catch(Exception e){
			log.error(e.getMessage());
			throw new InvalidUrlException();
		}
	}
}
