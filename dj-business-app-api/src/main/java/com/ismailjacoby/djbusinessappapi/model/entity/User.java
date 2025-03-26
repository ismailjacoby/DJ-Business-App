package com.ismailjacoby.djbusinessappapi.model.entity;

import com.ismailjacoby.djbusinessappapi.model.enums.MusicGenres;
import com.ismailjacoby.djbusinessappapi.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Table(name = "user_")
public class User extends BaseEntity implements UserDetails {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(name = "profile_picture")
    private String profilePictureUrl;

    // DJ Genres
    @ElementCollection(targetClass = MusicGenres.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_genres", joinColumns = @JoinColumn(name = "user_id"))
    private Set<MusicGenres> musicGenre;

    // Social Links
    private String instagram;
    private String facebook;
    private String youtube;
    private String mixcloud;
    private String soundcloud;
    private String tiktok;
    private String twitter;

    @Column(length = 1000)
    private String biography;
    private String pressKitUrl;

    // Location
    private String city;
    private String country;


    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.DJ;

    private boolean active = true;
    private boolean premium = false;

    // Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("Role_"+ role.name()));
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
        return active;
    }
}
