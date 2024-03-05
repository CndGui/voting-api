package com.guilhermerodrigues.votingapi.repository;

import com.guilhermerodrigues.votingapi.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
