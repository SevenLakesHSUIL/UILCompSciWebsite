package main.controller

import main.data.Division
import main.data.Team
import main.data.persistence.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

import java.security.Principal

@Controller
@RequestMapping("/team/register")
@SessionAttributes("team")
class TeamRegistrationController @Autowired
constructor(private val teamRepository: TeamRepository) {
    @InitBinder
    fun initBinder(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id", "score", "individuals")
    }

    @GetMapping
    fun teamregister(principal: Principal, model: Model): String {
        val team = teamRepository.findByUsername(principal.name).get()
        model.addAttribute("team", team)
        model.addAttribute("allDivisions", Division.values())
        return "team/register"
    }

    @PostMapping
    fun teamregister(@ModelAttribute team: Team): String {
        teamRepository.save(team)
        return "redirect:/"
    }
}
