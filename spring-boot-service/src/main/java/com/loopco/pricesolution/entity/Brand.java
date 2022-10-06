package com.loopco.pricesolution.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
}