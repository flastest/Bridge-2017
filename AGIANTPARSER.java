/***********************************************************************************
*  _             _             
* ╟ ⏋  _        ╟ ⏋  _
* ╟ |╱⃫╱   ___  ╟ |╱⃫╱
* ╟   ❬  //┎─┐⑊ ╟   ❬ 
* ╟ |╲ ╲ ┃┃☱⚌┙ ╟ |╲ ╲ 
* ╟▁⎭ ╲_⎭\\⚌⚌/ ╟▁⎭ ╲_⎭  
***********************************************************************************/

// All AGIANTPARSER does is take a stream (in this case the 
//  stuff from Main involving line length) and converts 
//  it to something I can use.
public class AGIANTPARSER{

	static int convertStream(java.io.InputStream is) {
   		java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
    	String ans = "";
	    try {
	    	String nex = s.next();
		    for (int i = 0; i < nex.length();i++){
		    	if (isInt(nex.substring(i,i+1))) {
		    		ans += nex.substring(i,i+1);
		    	}
		    }
		}
	    catch (Exception sodomy) {
	    	sodomy.printStackTrace();
	    }
	    return Integer.parseInt(ans);
	}

	static boolean isInt(String annoyingTeenager) {
		try {
			Integer.parseInt(annoyingTeenager);
			return true;
		}
		catch (Exception ragingAdult) {
			return false;
		}
	}
}