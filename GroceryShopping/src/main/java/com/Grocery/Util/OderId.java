package com.Grocery.Util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class OderId {
	public String generateOrderId() {
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    Random random = new Random();
	    StringBuilder sb = new StringBuilder("ORD-");

	    for (int i = 0; i < 6; i++) {
	        sb.append(chars.charAt(random.nextInt(chars.length())));
	    }
	    return sb.toString();
	}
}
