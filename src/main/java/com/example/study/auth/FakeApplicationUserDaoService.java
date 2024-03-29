package com.example.study.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.study.security.ApplicationUserRoles.ADMIN;
import static com.example.study.security.ApplicationUserRoles.ADMINTRAINEE;
import static com.example.study.security.ApplicationUserRoles.STUDENT;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        STUDENT.getGrantedAuthority(),
                        "student",
                        passwordEncoder.encode("student"),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser(
                        ADMIN.getGrantedAuthority(),
                        "admin",
                        passwordEncoder.encode("admin"),
                        true,
                        true,
                        true,
                        true
                )
                ,
                new ApplicationUser(
                        ADMINTRAINEE.getGrantedAuthority(),
                        "admintrainee",
                        passwordEncoder.encode("admintrainee"),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }
}
