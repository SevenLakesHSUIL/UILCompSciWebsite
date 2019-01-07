package main.data.persistence;

import main.data.Division;
import main.data.Individual;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IndividualRepository extends PagingAndSortingRepository<Individual, Integer> {
    @Query("select t from Individual t where t.name <> ''")
    Iterable<Individual> findAllExists();
    @Query("select t from Individual t where t.name <> ''")
    Iterable<Individual> findAllExists(Sort sort);
    @Query("select t from Individual t where t.division = ?1 and t.name <> ''")
    Iterable<Individual> findByDivisionAndExists(Division division);
    @Query("select t from Individual t where t.division = ?1 and t.name <> ''")
    Iterable<Individual> findByDivisionAndExists(Division division, Sort sort);
}