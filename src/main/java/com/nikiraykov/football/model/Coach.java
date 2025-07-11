package com.nikiraykov.football.model;

import jakarta.persistence.*;

@Entity
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private int experienceInYears;

    public Coach() {
    }

    public Coach(Long id, String nationality, String name, int experienceInYears) {
        this.id = id;
        this.nationality = nationality;
        this.name = name;
        this.experienceInYears = experienceInYears;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) {
        this.experienceInYears = experienceInYears;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "coach")
    private Team Team;

    public Team getTeam() {
        return Team;
    }

    public void setTeam(Team team) {
        Team = team;
    }
}
