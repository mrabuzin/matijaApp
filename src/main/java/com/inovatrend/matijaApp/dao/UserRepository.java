package com.inovatrend.matijaApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inovatrend.matijaApp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
