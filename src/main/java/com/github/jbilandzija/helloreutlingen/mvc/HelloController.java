package com.github.jbilandzija.helloreutlingen.mvc;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    private final Map<String, String> responseModelMap = new HashMap<>();

    Logger logger = LoggerFactory.getLogger(HelloController.class);

    /**
     * Initialize and populate Map for Example
     */
    public HelloController() {
        responseModelMap.put("1", "Response with Message 1");
        responseModelMap.put("2", "Response with Message 2");
        responseModelMap.put("3", "Response with Message 3");
        responseModelMap.put("4", "Response with Message 4");
        responseModelMap.put("5", "Response with Message 5");
        responseModelMap.put("6", "Response with Message 6");
        responseModelMap.put("7", "Response with Message 7");
        responseModelMap.put("8", "Response with Message 8");
        responseModelMap.put("9", "Response with Message 9");
    }

    /**
     * Default response
     */
    @RequestMapping("/")
    public String getGreeting() {
        logger.info("Greeting endpoint was called.");
        return "hello Reutlingen!";
    }

    /**
     * If you had enough!
     */
    @RequestMapping("/pause")
    public String getPause() {
        logger.info("Pause endpoint was called.");
        return "Dude.. Take a break!";
    }

    /**
     * Gets response from Map for a specific ID, which is passed as variable into this request
     */
    @GetMapping("/response/{id}")
    public String getResponseMapById(@PathVariable String id) {
        logger.info("Response endpoint was called by id " + id);
        return responseModelMap.get(id);
    }

    @GetMapping("/response")
    public Map<String, String> getResponseMap() {
        logger.info("General response endpoint was called");
        return responseModelMap;
    }

    @PostMapping("/response/{id}")
    public String createNewResponseById(@PathVariable String id, @RequestBody String body) throws IllegalAccessException {
        if (responseModelMap.containsKey(id)) {
            logger.error("Error during creation of new response id. Update not supported in POST!");
            throw new IllegalAccessException("Update not supported in POST!");
        }
        logger.info("New response id has been created. Id: " + id + ", Body: " + body);
        return responseModelMap.put(id, body);
    }

    @DeleteMapping("/response/{id}")
    public String deleteResponseById(@PathVariable String id) {
        logger.info("Response id has been deleted. Id: " + id);
        return responseModelMap.remove(id);
    }

    @PutMapping("/response/{id}")
    public String updateResponseById(@PathVariable String id, @RequestBody String body) throws IllegalAccessException {
        if (responseModelMap.containsKey(id)) {
            logger.info("Response id has been updated. Id: " + id);
            return responseModelMap.put(id, body);
        } else {
            logger.error("Failure during update. Create not supported in PUT! Id: " + id + ", Body: " + body);
            throw new IllegalAccessException("Create not supported in PUT!");
        }
    }
}
