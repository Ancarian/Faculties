package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;


@Getter
@Setter
@Entity
@Table(name = "subjects", indexes = {@Index(name = "id_index_subjects", columnList = "id", unique = true)})
public class Subject extends BaseObject {

    @Column(name = "subject", unique = true, nullable = false)
    private String subject;

    public Subject(Long id, String subject) {
        setId(id);
        this.subject = subject;
    }

    public Subject() {

    }
}
