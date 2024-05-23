package com.example.cookassistant.domain.user;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;


import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@JsonIncludeProperties({"id","email","authorities"})
public class MemberContext extends org.springframework.security.core.userdetails.User {

    private final long id;
    private final String email;
    private final Set<GrantedAuthority> authorities;

    public MemberContext(User user) {
        super(user.getEmail(), "", user.getAuthorities());
        id = user.getId();

        email = user.getEmail();
        authorities = user.getAuthorities().stream().collect(Collectors.toSet());

    }

}
