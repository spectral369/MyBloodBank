package com.bloodbank.utils;


import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vaadin.server.FileResource;

public class GLobalCache {
	
	
	private static  CacheLoader<String, FileResource> loaderUserIcons =
			new CacheLoader<String, FileResource>(){

			

				@Override
				public FileResource load(String arg0) throws Exception {
					// TODO Auto-generated method stub
					//System.out.println(arg0);
					return Utils.getUserImage(arg0);
				}
		
	};
	private static LoadingCache<String, FileResource> cacheUserIcons =
	    CacheBuilder.newBuilder().maximumSize(10).build(loaderUserIcons);
	

	
	public static FileResource getUserIcon(String key) {
		try {
		if(cacheUserIcons.get(key).getSourceFile().exists())
			return cacheUserIcons.get(key);
		else 
			return Utils.getUserImage("default");
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	public static void refreshUserIcon(String key) {
		cacheUserIcons.refresh(key);
	}
	private static  CacheLoader<String, FileResource> loaderIcons =
			new CacheLoader<String, FileResource>(){

			

				@Override
				public FileResource load(String arg0) throws Exception {
					// TODO Auto-generated method stub
				//	System.out.println(arg0);
					return Utils.getThemeResourceIcon(arg0);
				}
		
	};
	private static LoadingCache<String, FileResource> cacheIcons =
	    CacheBuilder.newBuilder().maximumSize(10).build(loaderIcons);
	

	
	public static FileResource getIcon(String key) {
		try {
			return cacheIcons.get(key);
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
	
	
	
	
	
	
}