package com.ProximityCalculatorApplication.ProximityCalculatorApplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Class containing endpoints. These have been tested using Postman
 */
@RestController
public class ProximityController {

    @RequestMapping("/")
    public String index() {
        return "start";
    }

    /**
     * This is the default mapping which will give you all people who are from london or are currently with 50 miles
     * of it.
     * @return JSON containing all the people from or near london.
     */
    @GetMapping("/london/50")
    public String getWithinLondonProximity() {
        LondonRequest londonRequest = new LondonRequest();
        return new Gson().toJson(londonRequest.getPeopleFromOrNearLondon(), new TypeToken<ArrayList<Person>>(){}.getType());
    }
}
