package org.tsdes.jee.exercises.quizgame.backend.entity;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arcuri82 on 30-Nov-16.
 */
@Entity
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @Size(max=128)
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<SubCategory> subCategories;

    public Category() {
        subCategories = new ArrayList<>();
    }

    public List<SubCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<SubCategory> subCategories) {
        this.subCategories = subCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
