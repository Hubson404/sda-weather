package org.hubson404.weather.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecurityUserRepository extends JpaRepository<SecurityUser,Long> {

    Optional<SecurityUser> findByUsername(String username);
}
