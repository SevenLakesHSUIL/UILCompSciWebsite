package main.data.persistence

import main.data.Division
import main.data.Individual
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
interface IndividualRepository : PagingAndSortingRepository<Individual, Int> {
    @Query("select t from Individual t where t.name <> ''")
    fun findAllExists(): Iterable<Individual>

    @Query("select t from Individual t where t.name <> ''")
    fun findAllExists(sort: Sort): Iterable<Individual>

    @Query("select t from Individual t where t.division = ?1 and t.name <> ''")
    fun findByDivisionAndExists(division: Division): Iterable<Individual>

    @Query("select t from Individual t where t.division = ?1 and t.name <> ''")
    fun findByDivisionAndExists(division: Division, sort: Sort): Iterable<Individual>
}