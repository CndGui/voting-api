package com.guilhermerodrigues.votingapi.dto;

import com.guilhermerodrigues.votingapi.entity.Session;
import com.guilhermerodrigues.votingapi.entity.Vote;

public record SessionResponseDTO(Long id, Long topicID, Long votes, Long yesVotes, Long noVotes) {
    public SessionResponseDTO(Session session) {
        this(
            session.getId(),
            session.getTopic().getId(),
            (long) session.getVotes().size(),
            session.getVotes().stream().filter(Vote::getChoice).count(),
            session.getVotes().stream().filter(vote -> !vote.getChoice()).count()
        );
    }
}
