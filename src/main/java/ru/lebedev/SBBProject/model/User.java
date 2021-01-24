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
        message = "Пароли не совпадают",
        groups = {PasswordGroup.class})
public class User {
    @Id
    @Column(name = "username", columnDefinition = "VARCHAR(64)")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Неверный формат")
    @NotBlank(message = "Требуется ввести имя пользователя")
    private String username;
    @Column(name = "password")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Неверный формат", groups = {PasswordGroup.class})
    @NotBlank(message = "Требуется ввести пароль")
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