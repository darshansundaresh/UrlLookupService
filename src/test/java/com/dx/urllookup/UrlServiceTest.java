package com.dx.urllookup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.dx.urllookup.domain.SiteInfo;
import com.dx.urllookup.exceptions.InvalidUrlException;
import com.dx.urllookup.service.UrlService;
import com.dx.urllookup.service.UrlServiceImpl;
import com.dx.urllookup.utils.UrlUtils;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

	String encodedUrlWithPathAndQuery="/urlinfo/1/https%3A%2F%2Fwww.hjaoopoa.top%2Fadmin.php%3Ff%3D1.gif";
	String encodedAddUrlWithPathAndQuery="/urladd/1/https%3A%2F%2Fwww.hjaoopoa.top%2Fadmin.php%3Ff%3D1.gif";
	String validUrlWithPathAndQuery="hjaoopoa.top/admin.php?f=1.gif";
	String addApiBasePath="/urladd/1/";
	String verifyApiBasePath="/urlinfo/1/";
	SiteInfo suspeciousSite;
	
	
	@InjectMocks
	UrlServiceImpl urlService=new UrlServiceImpl();
	UrlUtils urlUtils=new UrlUtils();
	
	@Mock
	RedisTemplate<String, Object> redisTemplate;
	
	@Mock
	ValueOperations<String, Object> valueOps;
	
	@Before
	public void setupMock(){
		suspeciousSite=new SiteInfo(validUrlWithPathAndQuery, "true");
		MockitoAnnotations.initMocks(this);
		
		when(valueOps.get(validUrlWithPathAndQuery)).thenReturn((Object)"true");
		//doNothing().when(valueOps).set(validUrlWithPathAndQuery, "false");
		
		when(redisTemplate.opsForValue()).thenReturn(valueOps);
		
		urlService.setUrlUtils(urlUtils);
		urlService.setAddApiBasePath(addApiBasePath);
		urlService.setVerifyApiBasePath(verifyApiBasePath);
		
	}
	
	@Test
	public void testMockCreation(){
		assertNotNull(suspeciousSite);
		assertNotNull(urlService);
		assertNotNull(redisTemplate);
		assertNotNull(valueOps);
	}
	
	
	@Test
	public void testRedisTemplate(){
		assertEquals("true", (String)redisTemplate.opsForValue().get(validUrlWithPathAndQuery));
		
				
	}
	
	@Test
	public void testUrlService(){
		assertNotNull(urlService.verifySite(encodedUrlWithPathAndQuery));
		assertEquals(suspeciousSite.getFullUrl(), urlService.verifySite(encodedUrlWithPathAndQuery).getFullUrl());
	}
	
	@Test(expected=InvalidUrlException.class)
	public void testInvalidUrl(){
		urlService.verifySite(verifyApiBasePath);
	}
	
	
	@Test
	public void testAddUrlService(){
		assertEquals(suspeciousSite.getFullUrl(), urlService.addMaliciousSite(encodedAddUrlWithPathAndQuery).getFullUrl());
		assertEquals(suspeciousSite.getIsMalicious(),urlService.addMaliciousSite(encodedAddUrlWithPathAndQuery).getIsMalicious());
	}
	
	
	
}
