package dev.chermenin.dao;

import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.adress.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {


}
