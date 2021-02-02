package com.aom.support;

import java.util.Random;

public class DataGenerator {
		public String getDealReferenceNumber(String brand) {
		Random rand = new Random();
		int number = rand.nextInt(1000000000);
		String dealRef = brand + "-" + Integer.toString(number);
		return dealRef;
	}

	
}
