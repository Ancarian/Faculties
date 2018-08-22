package dev.chermenin.model.impl.adress;

import dev.chermenin.model.impl.BaseObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "city")
@Getter
@Setter
@ToString
public class City extends BaseObject {
    @Column(nullable = false, length = 10)
    private String name;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;
}
