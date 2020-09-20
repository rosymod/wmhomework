package com.wemakeprice.homework.kst;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Comparator;

@SpringBootTest
class KstApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void sortWords(){
		String[] values = "avAedfASesDfAa".split("");
		Arrays.sort(values,new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				System.out.println(o1 + " | " + o2 + " = " + o1.compareTo(o2));
				if(o1.compareToIgnoreCase(o2) == 0 && !o2.equals(o1) && o2.toUpperCase().equals(o1)){
					return -1;
				}else{
					return o1.compareToIgnoreCase(o2);
				}
			}
		});
		for(String value : values){
			System.out.println(value);
		}
	}
}
