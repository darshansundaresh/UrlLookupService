package com.dx.urllookup;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dx.urllookup.exceptions.InvalidUrlException;
import com.dx.urllookup.utils.UrlUtils;

public class UrlUtilsTest {

	UrlUtils urlUtils=new UrlUtils();
	String encodedUrlWithPathAndQuery="/urlinfo/1/https%3A%2F%2Fwww.hjaoopoa.top%2Fadmin.php%3Ff%3D1.gif";
	String encodedUrlWithoutPathAndQuery="/urlinfo/1/hjaoopoa.top";
	String encodedUrlWithPortNumbers="/urlinfo/1/https%3A%2F%2Fhjaoopoa.top%3A443%2Fadmin.php%3Ff%3D1.gif";
	String encodedUrlWithoutScheme="hjaoopoa.top%2Fadmin.php%3Ff%3D1.gif";
	
	String validUrlWithPathAndQuery="hjaoopoa.top/admin.php?f=1.gif";
	String validUrlWithoutPathAndQuery="hjaoopoa.top";
	
	String verifyApiBasePath="/urlinfo/1/";
	
	@Test
	public void validateNormalizationWithoutPathAndQuery(){
		assertEquals(validUrlWithoutPathAndQuery, urlUtils.normalizeUrl(encodedUrlWithoutPathAndQuery, verifyApiBasePath));
	}
	
	@Test
	public void validateNormalizationWithPathAndQuery(){
		
		assertEquals(validUrlWithPathAndQuery, urlUtils.normalizeUrl(encodedUrlWithPathAndQuery, verifyApiBasePath));
	}
	
	@Test
	public void validateNormalizationWithPort(){
		assertEquals(validUrlWithPathAndQuery,urlUtils.normalizeUrl(encodedUrlWithPortNumbers, verifyApiBasePath));
	}
	
	@Test
	public void validateNormalizationWithoutScheme(){
		assertEquals(validUrlWithPathAndQuery,urlUtils.normalizeUrl(encodedUrlWithoutScheme, verifyApiBasePath));
	}
	
	@Test(expected=InvalidUrlException.class)
	public void verifyInvalidUrlExceptionTest(){
		urlUtils.normalizeUrl("", verifyApiBasePath);
	}
}
