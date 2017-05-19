/**
 * 
 */
package assgn.stock.utils;

import java.util.Collections;
import java.util.Map;

/**
 * @author Jogireddy Kotam
 *
 */
public class String2MapUtil {
    
    private String2MapUtil() {
        //default private
    }
	
	public static Map<String, String> convert(String key, String value){
		return Collections.singletonMap(key, value);
	}

}
