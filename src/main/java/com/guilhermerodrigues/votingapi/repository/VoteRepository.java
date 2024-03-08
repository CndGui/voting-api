package com.guilhermerodrigues.votingapi.repository;

import com.guilhermerodrigues.votingapi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
