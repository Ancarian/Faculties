package dev.chermenin.dao;

import dev.chermenin.model.impl.adress.City;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class CityRepositoryTest {
    @Autowired
    private CityRepository cityRepository;

    @BeforeAll
    public void setUp() {
        if (cityRepository.count() == 0) {
            Assert.fail("database is empty");
        }
    }

    @Test
    @Transactional(readOnly = true)
    public void findAllByCountryIdAndNameLike() {
        Long countryId = 1L;
        String cityName = "Borisov";
        List<City> cityList = cityRepository.findAllByCountryIdAndNameLike(countryId, cityName,
                PageRequest.of(0, 10)).getContent();

        if (cityList.isEmpty()) {
            Assert.fail("city list is empty");
        }
        for (City city : cityList) {
            Assert.assertEquals(countryId, city.getCountry().getId());
            Assert.assertEquals(cityName, city.getName());
        }
    }
}