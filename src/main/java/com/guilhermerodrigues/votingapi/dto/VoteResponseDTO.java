package com.guilhermerodrigues.votingapi.dto;

import com.guilhermerodrigues.votingapi.entity.Vote;

public record VoteResponseDTO(Long id, Long cpf, Boolean choice, Long sessionID) {
    public VoteResponseDTO(Vote vote) {
        this(
            vote.getId(),
            vote.getCpf(),
            vote.getChoice(),
            vote.getSession().getId()
        );
    }
}
