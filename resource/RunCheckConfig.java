package jadore;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunCheckConfig {

	private RunCheckConfig(String paramString) throws Exception {
	    Process lp = Runtime.getRuntime().exec(paramString); 
	    BufferedReader lbr = new BufferedReader(new InputStreamReader(lp.getInputStream()));
	    String s1;
	    StringBuffer lsb = new StringBuffer();
	    
	    while( (s1 = lbr.readLine()) != null){
	    	lsb.append(s1).append("\n");
	    }
	    String str2 = lsb.toString(); 
	    Exception localException = new Exception(str2); 
	    throw localException;	
	}

}
