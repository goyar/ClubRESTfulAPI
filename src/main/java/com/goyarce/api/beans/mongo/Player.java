package com.goyarce.api.beans.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document(collection="players")
public class Player {

    @Id
    public String id;

    @Field("Team")
    private String team;

    @Field("Guardian")
    private String guardian;

    @Field("FirstName")
    private String firstName;

    @Field("LastName")
    private String lastName;

    @Field("Birthdate")
    private Date birthdate;

    public Player(){}

    public Player(String team, String guardian, String firstName, String lastName, Date birthdate) {
        this.team = team;
        this.guardian = guardian;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        team = team;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        guardian = guardian;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        birthdate = birthdate;
    }

    @Override
    public String toString(){
        return "Player: {" + getFirstName() + ", " + getLastName() + "}";
    }
}