package com.springboot.valueAnnotations.controller;

import com.springboot.valueAnnotations.model.MyLocalDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DefaultController {

    Logger logger = LoggerFactory.getLogger(DefaultController.class);

    @Value("${main.app.greeting.message:Some default greeting message if the key doesn't exists in the properties file}")
    private String greetingMessage;
    @Value("${main.app.greeting.messageList:One,Two,Three,Four,Five}")
    private List<String> greetingMessageList;
    @Value("#{${main.app.greeting.map}}")
    private Map<String, String> greetingsMap;

    @Autowired
    MyLocalDatasource myLocalDatasource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void defaultController() {
        logger.info(greetingMessage);
        greetingMessageList.forEach(greeting-> logger.info(greeting));
        greetingsMap.entrySet().forEach(greetingsEntry-> logger.info(greetingsEntry.getKey()+":"+greetingsEntry.getValue()));

        logger.info(myLocalDatasource.toString());
    }
}
