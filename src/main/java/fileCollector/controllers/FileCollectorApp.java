package fileCollector.controllers;

import fileCollector.interfaces.IOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileCollectorApp {

    @Autowired
    private IOrchestrator iOrchestrator;

    @RequestMapping(value = "/tabs/get", method = RequestMethod. GET)
    @ResponseBody
    public String getTabs()
    {
        return iOrchestrator.getTabs();
    }


}
