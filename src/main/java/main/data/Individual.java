package main.data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Individual implements Comparable<Individual> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int score;

    @NotBlank
    private String school;
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Division division;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    public Team team;

    private Individual() {
    }

    Individual(String school, String name, Team team, Division division) {
        this.school = school;
        this.name = name;
        this.team = team;
        this.division = division;
    }

    public String getSchool() {
        return school;
    }

    void setSchool(String school) {
        this.school = school;
    }

    public Division getDivision() {
        return division;
    }

    void setDivision(Division division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    void setScore(int score) {
        this.score = score;
    }

    public Team getTeam() {
        return team;
    }

    void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int compareTo(Individual o) {
        if(o.score != score) {
            return Integer.compare(o.score, score);
        }
        return name.compareTo(o.name);
    }
}
