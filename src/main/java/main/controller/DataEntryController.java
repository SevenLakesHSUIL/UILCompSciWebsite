package main.controller;

import com.google.common.collect.Lists;
import main.data.Individual;
import main.data.persistence.IndividualRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/data/entry")
@SessionAttributes("indiEntryDTO")
public class DataEntryController {
    private final IndividualRepository individualRepository;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id", "school", "name", "division", "team");
    }

    @Autowired
    public DataEntryController(IndividualRepository individualRepository) {
        this.individualRepository = individualRepository;
    }

    @GetMapping
    public String dataentry(Model model) {
        model.addAttribute("indiEntryDTO", new IndiEntryDTO(individualRepository.findAllExists(sortByTeamIDAsc())));
        return "data/entry";
    }

    @PostMapping
    public String teamregister(@Valid @ModelAttribute IndiEntryDTO indiEntryDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "data/entry";
        }
        individualRepository.saveAll(indiEntryDTO.getIndividuals());
        return "redirect:/data/entry";
    }

    private Sort sortByTeamIDAsc() {
        return new Sort(Sort.Direction.ASC, "team.id");
    }

    private static class IndiEntryDTO {
        @Valid
        private List<Individual> individuals;

        public IndiEntryDTO(Iterable<Individual> individuals) {
            this.individuals = Lists.newArrayList(individuals);
        }

        public List<Individual> getIndividuals() {
            return individuals;
        }
    }
}
