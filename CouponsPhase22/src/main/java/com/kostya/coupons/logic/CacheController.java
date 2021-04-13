package com.kostya.coupons.logic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class CacheController {
	private Map<String, Object> dataMap;
	
	public CacheController() {
		this.dataMap = new ConcurrentHashMap<>();
	}
	public void put(String key, Object value) {
		this.dataMap.put(key, value);
	}
	public Object get(String key) {
		return this.dataMap.get(key);
	}
	public void delete(String key) { //logout and delete user
		this.dataMap.remove(key);
	}
	
}
