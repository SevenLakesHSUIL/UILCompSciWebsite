package main.data.persistence;

import main.data.Division;
import main.data.Individual;
import main.data.Team;
import main.data.UserDetailsImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IndividualRepository extends CrudRepository<Individual, Integer> {
    @Query("select t from Individual t where t.school = ?1 and t.name <> ''")
    List<Individual> findBySchoolAndExists(String school);
    @Query("select t from Individual t where t.division = ?1 and t.name <> ''")
    List<Individual> findByDivisionAndExists(Division division);
    @Query("select t from Individual t where t.division = ?1 and t.name <> ''")
    List<Individual> findByDivisionAndExists(Division division, Sort sort);
}