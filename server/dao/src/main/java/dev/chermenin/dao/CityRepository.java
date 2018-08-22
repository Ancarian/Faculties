package dev.chermenin.dao;

import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.adress.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    Page<City> findAllByCountryIdAndNameLike(Long id, String name, Pageable pageable);
}
