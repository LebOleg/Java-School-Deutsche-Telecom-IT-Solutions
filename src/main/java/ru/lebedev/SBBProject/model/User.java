package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @Column(name = "username", columnDefinition="VARCHAR(64)")
    private String username;
    @Column(name = "password")
    private String password;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany
    @JoinTable(name = "authorities",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private List<Role> roles;
}