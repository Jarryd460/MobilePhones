package za.ac.cput.MobilePhones.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by student on 2015/10/18.
 */

@RestController
@RequestMapping("/")
public class LandingPage {

    @RequestMapping(method = RequestMethod.GET)
    public String getWelcome(){
        return "Welcome to the Landing Page";
    }

}
