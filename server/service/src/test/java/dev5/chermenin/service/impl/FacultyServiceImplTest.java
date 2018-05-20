package dev5.chermenin.service.impl;

import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.FacultyService;
import dev5.chermenin.service.dto.impl.FacultyDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
@ComponentScan(basePackages = {"dev5.chermenin"})
public class FacultyServiceImplTest {
    private final static Pageable pageable = new PageRequest(0, 100);
    @Autowired
    private FacultyService facultyService;

    @Test
    public void findById() {
        assertEquals(facultyService.findById(1L).getId(), (Long) 1L);
    }

    @Test
    public void findAll() {
        assertEquals(Long.valueOf(facultyService.findAll(pageable).size()), (Long) 1L);
    }

    @Test
    public void save() {
        FacultyDto facultyDto = new FacultyDto();
        facultyDto.getUniversityDto().setId(1L);
        facultyDto.setName("name22");
        facultyDto.setInformation("info22");
        facultyDto.getUniversityDto().setId(1L);
        facultyService.save(facultyDto);
    }

    @Ignore
    @Test
    public void remove() {
        facultyService.remove(1L);
    }
}