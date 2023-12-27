package hac.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.Set;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //this is the validation for the field name in createTeam form

    @NotEmpty(message = " Team name is required")
    @Pattern(regexp = "[A-Za-z\\s]+", message = " Team name should only contain characters")
    private String name;

    private int score;

    public int getScore() {
        return this.score ;
    }

    public void setScore(int score){
         this.score = score;
    }

    public void setName(String teamName) {
        this.name = teamName;
    }

    public String getName() {
        return this.name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
