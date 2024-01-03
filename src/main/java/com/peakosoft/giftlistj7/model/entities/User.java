package com.peakosoft.giftlistj7.model.entities;

import com.peakosoft.giftlistj7.model.enums.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String name;
    private String lastName;
    private String birthday;
    private String phoneNumber;
    private String password;
    private String hobby;
    private String important;
    private String email;
    @Enumerated(EnumType.STRING)
    private Country country;
    @Enumerated(EnumType.STRING)
    private ClothesSize clothesSize;
    @Enumerated(EnumType.STRING)
    private ShoesSize shoesSize;
    @Enumerated(EnumType.STRING)
    private SocialMedia socialMedia;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean subscribe;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Booking> booking;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "users_notifications",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private List<Notification> notifications;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Complaint> complaints;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Gift> gifts;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private List<Holiday> myHolidays;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        return grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return email;
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
}
