package de.welt.controller;

import de.welt.service.UserDataService;
import de.welt.domain.MergedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @RequestMapping(method = RequestMethod.GET, value = "/user-data/{userId}")
    private MergedResponse getUserData(@PathVariable("userId") String userId) {
        return userDataService.requestForUser(userId);
    }

}
