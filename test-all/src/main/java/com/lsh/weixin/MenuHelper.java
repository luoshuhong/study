package com.lsh.weixin;


import java.util.List;

import weixin.popular.api.MenuAPI;
import weixin.popular.bean.Menu;
import weixin.popular.bean.MenuButtons;
import weixin.popular.bean.MenuButtons.Button;
import weixin.popular.bean.Token;

public class MenuHelper {
	public static void deleteMenu(Token token) {
		MenuAPI.menuDelete(token.getAccess_token());
	}
	
	public static void traceMenu(Token token) {
		System.out.println("traceMenu start:");
		Menu menu = MenuAPI.menuGet(token.getAccess_token());
		
		Button[] buttons = menu.getMenu().getButton();
		
		for (Button button:buttons) {
			System.out.println(button.getName());
			System.out.println(button.getType());
			System.out.println(button.getUrl());
			System.out.println(button.getKey());
			List<Button> subs = button.getSub_button();
			
			for (Button sub:subs) {
				
				System.out.println("\t" + sub.getName());
				System.out.println("\t" + sub.getType());
				System.out.println("\t" + sub.getUrl());
				System.out.println("\t" + button.getKey());
			}
		}
		
		System.out.println("traceMenu end");
	}
	
	public static void createMenu(Token token, MenuButtons buttons) {
		MenuAPI.menuCreate(token.getAccess_token(), buttons);
	}
	
}
