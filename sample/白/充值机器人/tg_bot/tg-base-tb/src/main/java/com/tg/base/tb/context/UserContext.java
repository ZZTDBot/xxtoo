package com.tg.base.tb.context;


public class UserContext {

	private static final ThreadLocal<TokenUser> USER_HODLER = new ThreadLocal<>();

	public static void setUser(TokenUser user){
		USER_HODLER.set(user);
	}

	public static void remove(){
		USER_HODLER.remove();
	}

	public static TokenUser getUser(){
		return USER_HODLER.get();
	}
}
