package com.dx.urllookup.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

	private Logger log = LoggerFactory.getLogger(DataLoader.class);
	
	@Value("${urlservice.load_seed_data:false}")
	private boolean loadSeedData;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		if(loadSeedData){
			String fileName = "seed/blocklist.txt";
	        ClassLoader classLoader = new DataLoader().getClass().getClassLoader();
	        InputStream in=classLoader.getResourceAsStream(fileName);
	        BufferedReader reader=null;
	        try{
	 
	        	reader=new BufferedReader( new InputStreamReader(in));
	        	String line=reader.readLine();
	        	
	        	while(line!=null){
	        		log.info("Seeding:"+line);
	        		redisTemplate.opsForValue().set(line.trim(),"true");
	        		line=reader.readLine();
	        	}
	        }catch(Exception e){
	        	log.error(e.getMessage());
	        }finally{
	        	if(reader!=null)
	        	reader.close();
	        }
		}
		
	}

}
