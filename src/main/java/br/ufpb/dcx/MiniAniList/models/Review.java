package br.ufpb.dcx.MiniAniList.models;

import jakarta.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private int nota;

    @Column
    private String comentario;
}
