package dev.chermenin.model.impl.adress;


import dev.chermenin.model.impl.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country extends BaseObject {
    @Column(nullable = false, length = 5)
    private Integer phoneCode;
    @Column(nullable = false, length = 5)
    private String shortName;
    @Column(nullable = false, length = 10)
    private String name;
}
