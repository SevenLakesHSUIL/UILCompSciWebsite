package main.controller

import kotlinx.collections.immutable.toImmutableList
import main.data.Individual
import main.data.persistence.IndividualRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@Controller
@RequestMapping("/data/entry")
@SessionAttributes("indiEntryDTO")
class DataEntryController @Autowired
constructor(private val individualRepository: IndividualRepository) {
    @InitBinder
    fun initBinder(dataBinder: WebDataBinder) {
        dataBinder.setDisallowedFields("id", "school", "name", "division", "team")
    }

    @GetMapping
    fun dataentry(model: Model): String {
        model.addAttribute("indiEntryDTO", IndiEntryDTO(individualRepository.findAllExists(sortByTeamIDAsc())))
        return "data/entry"
    }

    @PostMapping
    fun dataentry(@Valid @ModelAttribute indiEntryDTO: IndiEntryDTO, bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) {
            return "data/entry"
        }

        for (i in indiEntryDTO.individuals) {
            try {
                individualRepository.save(i)
            } catch (ignored: ObjectOptimisticLockingFailureException) {
            }

        }
        return "redirect:/data/entry"
    }

    private fun sortByTeamIDAsc(): Sort = Sort(Sort.Direction.ASC, "team.id")

    class IndiEntryDTO internal constructor(individuals: Iterable<Individual>) {
        @Valid
        internal val individuals: List<Individual> = individuals.toImmutableList()

    }
}
