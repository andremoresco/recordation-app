package com.recordation.usermanagementservice.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required!")
    private String name;

    @Column(name = "user_identifier", unique = true, nullable = false)
    @NotBlank(message = "User Identifier is required!")
    private String userIdentifier;

    @Column(nullable = false)
    @NotBlank(message = "Email is required!")
    private String email;

    private String picture;

    @Column(nullable = false)
    @NotBlank(message = "Password is required!")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userIdentifier;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Optional<String> isNotValid() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(this);

        if (violations.isEmpty()) {
            return Optional.empty();
        }

        String reduce = violations.stream()
                .map(ConstraintViolation::getMessageTemplate)
                .reduce("", (result, next) -> result + " \n" + next);

        return Optional.of(reduce);
    }
}
