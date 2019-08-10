package com.accolite.au;

import java.util.HashMap;
import java.util.Map;

public class Db {

	static Map<String, User> loginMap = new HashMap<String,User>();
	
	static {
		
		loginMap.put("nikhil",new User((Integer)1,"nikhil","nikhil",3,(long)0,(Integer)1, (long)0, (long)0,1,1,1));
		loginMap.put("kunal",new User((Integer)2,"kunal","kunal",3,(long)0,(Integer)1, (long)0, (long)0,1,1,1 ));
		loginMap.put("guddu",new User((Integer)3,"guddu","guddu",3,(long)0,(Integer)1, (long)0, (long)0,1,1,1));
		loginMap.put("pranali",new User((Integer)4,"pranali","pranali",3,(long) 0,(Integer)1, (long)0, (long)0,1,1,1));
		
	}

		
		
	
	
	
	
	
	

}
