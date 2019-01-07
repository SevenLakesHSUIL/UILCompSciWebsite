package main.controller;

import main.data.Division;
import main.data.Individual;
import main.data.persistence.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndividualLeaderboardController {
    private IndividualRepository individualRepository;

    @Autowired
    public IndividualLeaderboardController(IndividualRepository individualRepository) {
        this.individualRepository = individualRepository;
    }

    @GetMapping("/indiranks")
    public String indiranks(Model model) {
        Iterable<Individual> noviceRanks = individualRepository.findByDivisionAndExists(Division.NOVICE, sortByScoreDesc());
        model.addAttribute("noviceRanks", noviceRanks);

        Iterable<Individual> advancedRanks = individualRepository.findByDivisionAndExists(Division.ADVANCED, sortByScoreDesc());
        model.addAttribute("advancedRanks", advancedRanks);

        return "indiranks";
    }

    private Sort sortByScoreDesc() {
        return new Sort(Sort.Direction.DESC, "score");
    }
}
