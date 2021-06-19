package io.kyorg.springsecuritydemo.appuser;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private AppUserRole appUserRole;

    @Column(name = "role_description")
    private String roleDescription;

    @Override
    public String getAuthority() {
        return appUserRole.toString();
    }

    public static Authority createAuthority(AppUserRole role, String description) {
        Authority authority = new Authority();
        authority.setAppUserRole(role);
        authority.setRoleDescription(description);
        return authority;
    }
}
