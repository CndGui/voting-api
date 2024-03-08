package com.guilhermerodrigues.votingapi.entity;

import com.guilhermerodrigues.votingapi.dto.VoteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "votes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cpf;
    private Boolean choice;

    @ManyToOne(cascade = CascadeType.ALL)
    private Session session;

    public Vote(VoteRequestDTO data, Session session) {
        this.cpf = data.cpf();
        this.choice = data.choice();
        this.session = session;
    }
}
