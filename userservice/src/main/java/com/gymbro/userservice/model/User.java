package com.gymbro.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, name = "firstname") // Password should not be null
    private String firstName;

    @Column(nullable = false, name = "lastname") // Password should not be null
    private String lastName;

    @Column(nullable = false, unique = true) // Ensures unique username
    private String username;

    @Column(nullable = false) // Password should not be null
    private String password;

    @Column(nullable = false, unique = true) // Ensures unique email
    private String email;

    @Column
    private boolean enabled;

    @Column(name = "roles")
    private String roles;

    @Column(name = "account_non_expired")
    private boolean isAccountNonExpired;

    @Column(name = "account_non_locked")
    private boolean isAccountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean isCredentialsNonExpired;

    @CreationTimestamp
    @Column(updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    // implement userDetails

    // getUsername() and getPassword() are generated with Lombok

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // Adjust according to your role management
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
        return enabled;
    }
}
