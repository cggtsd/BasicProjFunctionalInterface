package cgg.functionalinterface;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class BiConsumerExample {

	public static void main(String[] args) {
		Map<Integer,String> map=new HashMap<>();
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		
		//BiConsumer<Integer,String> biConsumer= (k,v)->System.out.println("Key: "+k+"\t"+"Value: "+v);
		
		map.forEach((k,v)->System.out.println("Key: "+k+"\t"+"Value: "+v));
		
		/*
		 * Map<Integer,Student> stuMap= new HashMap<>(); stuMap.put(1001, new
		 * Student("Martin",9043333553L)); stuMap.put(1002, new
		 * Student("Frank",5043333553L));
		 * System.out.println("-----------------------------");
		 * stuMap.forEach((k,v)->System.out.println("Key: "+k+"\t"+"Value: "+v));
		 */	
	}

}
