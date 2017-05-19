/**
 * 
 */
package assgn.stock.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Jogireddy Kotam
 * @date 17-May-2017
 *
 */
@Controller
@RequestMapping("/")
public class MainController {
    
    private static final Logger logger = LogManager.getLogger(MainController.class);
    
    public String index() {
        logger.info("Entered index");
        return "webapp/index.html";
    }

}
