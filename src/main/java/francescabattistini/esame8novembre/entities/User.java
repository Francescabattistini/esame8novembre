package francescabattistini.esame8novembre.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"password", "role", "accountNonLocked",
        "credentialsNonExpired", "accountNonExpired", "authorities", "enabled"})// così passw e ruolo non sono inclusi nel json
public class User implements UserDetails  {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private long id;
    private String name;
    private String secondname;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @JsonIgnore//evita cicli infiniti quando lo convertiamo in json
    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;
    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private List <Event> eventList;



    public User(String name, String secondname, String email, String password) {
        this.name = name;
        this.secondname = secondname;
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
