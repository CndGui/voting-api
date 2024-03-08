package com.guilhermerodrigues.votingapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Topic topic;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();

    public Session(Topic topic) {
        this.topic = topic;
    }
}
