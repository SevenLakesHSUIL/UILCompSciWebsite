package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DataEntryController {
    @GetMapping("/data/entry")
    public String dataentry(Model model) {
        return "data/entry";
    }
}
