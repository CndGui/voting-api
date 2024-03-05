package com.guilhermerodrigues.votingapi.repository;

import com.guilhermerodrigues.votingapi.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRespository extends JpaRepository<Topic, Long> {
}
