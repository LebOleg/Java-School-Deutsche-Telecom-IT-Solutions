package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Role {

    public Role(String role) {
        this.role = role;
    }

    @Id
    @Column(name = "authority",  columnDefinition = "VARCHAR(64)")
    private String role;
    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}