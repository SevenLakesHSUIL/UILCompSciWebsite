package main.controller;

import main.data.Division;
import main.data.Individual;
import main.data.Team;
import main.data.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Locale;

@Controller
@RequestMapping("/team/register")
@SessionAttributes("team")
public class TeamRegistrationController {

    private TeamRepository teamRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "score", "individuals");
    }

    @Autowired
    public TeamRegistrationController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public String teamregister(Principal principal, Model model) {
        Team team = teamRepository.findByUsername(principal.getName());
        model.addAttribute("team", team);
        model.addAttribute("allDivisions", Division.values());
        return "team/register";
    }

    @PostMapping
    public String teamregister(@ModelAttribute Team team) {
        teamRepository.saveAndFlush(team);
        return "redirect:/";
    }
}
