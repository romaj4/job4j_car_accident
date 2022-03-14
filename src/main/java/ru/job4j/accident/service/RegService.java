package ru.job4j.accident.service;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Service
public class RegService {
    private final PasswordEncoder encoder;

    private final UserRepository users;

    private final AuthorityRepository authorities;

    public RegService(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    public String getPassword(User user) {
        return encoder.encode(user.getPassword());
    }

    public Authority getAuthority(String role) {
        return authorities.findByAuthority(role);
    }

    public void save(User user) {
        users.save(user);
    }

    public boolean checkUserName(User user) {
        return users.findByUsername(user.getUsername()) == null;
    }
}
