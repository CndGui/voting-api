package com.guilhermerodrigues.votingapi.dto;

public record VoteRequestDTO(Long cpf, Boolean choice, Long sessionID) {
}
