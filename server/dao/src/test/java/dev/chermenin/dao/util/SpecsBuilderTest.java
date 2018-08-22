package dev.chermenin.dao.util;

import dev.chermenin.model.impl.Group;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.Assert.*;

public class SpecsBuilderTest {
    @Test
    public void createSpecification() {
        String search = "information:koko,limit:4,name_city:borisov";
        Specification<Group> groupSpecification2 = new SpecsBuilder<Group>().createSpecification(search);
    }
}