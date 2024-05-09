package javau9.ca.db.abc.finalprojectalternativehf.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name = "users")
public class SimpleUser implements UserDetails {

    @Enumerated(EnumType.STRING)
    private Role role;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
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

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(unique = true)
    private String username;

    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    @Setter
    @Getter
    @NotBlank(message = "Password is mandatory")
    private String password;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @OneToMany(mappedBy = "simpleUser", cascade = CascadeType.ALL)
    private List<Subscription> subscriptions = new ArrayList<>();


    @Setter
    @Getter
    @OneToMany(mappedBy = "simpleUser", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    public SimpleUser() {

    }

    public SimpleUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


}


