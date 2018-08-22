package dev.chermenin.model.impl;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "subjects")
public class Subject extends BaseObject {

    @Column(name = "subject", unique = true, nullable = false)
    private String subject;
}
