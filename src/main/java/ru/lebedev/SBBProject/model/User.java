package ru.lebedev.SBBProject.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.lebedev.SBBProject.validation.PasswordGroup;
import ru.lebedev.SBBProject.validation.FieldMatch;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@FieldMatch(first = "password", second = "matchingPassword",
        message = "The password fields must match",
        groups = {PasswordGroup.class})
public class User {
    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(64)")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "wrong format")
    @NotBlank(message = "username is required")
    private String username;
    @Column(name = "password")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "wrong format", groups = {PasswordGroup.class})
    @NotBlank(message = "password is required")
    private String password;
    @Transient
    private String matchingPassword;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @Column(name = "enabled")
    private Boolean enabled;
    @ManyToMany
    @JoinTable(name = "authorities",
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    private List<Role> roles;
}