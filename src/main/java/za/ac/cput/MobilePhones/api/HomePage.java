package za.ac.cput.MobilePhones.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by student on 2015/10/18.
 */

@RestController
@RequestMapping("/api/**")
public class HomePage {

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public String Index(){
        return "This is a Home Page";
    }

}
