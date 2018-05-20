package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.SubjectRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.service.api.SubjectService;
import dev5.chermenin.service.dto.impl.subject.SubjectDto;
import dev5.chermenin.service.exceptions.ExistsException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ancarian on 01.12.2017.
 */
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(SubjectServiceImpl.class);

    @Override
    public SubjectDto findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("subject's name can't be null");
        }
        return new SubjectDto(subjectRepository.findBySubject(name));
    }

    @Override
    public SubjectDto findById(Long id) {
        return new SubjectDto(subjectRepository.findOne(id));
    }

    @Override
    @Transactional
    public SubjectDto save(SubjectDto subjectDto) {
        if (subjectDto == null) {
            throw new IllegalArgumentException("subject can't be null");
        }

        if (subjectRepository.findBySubject(subjectDto.getSubject()) != null) {
            throw new ExistsException("subject exists");
        }

        Subject subject = new Subject();
        subject.setSubject(subjectDto.getSubject());
        return new SubjectDto(subjectRepository.save(subject));
    }

    @Override
    @Transactional
    public void remove(Long id) {
        subjectRepository.delete(id);
    }

    @Override
    @Transactional
    public void removeAll() {
        subjectRepository.deleteAll();
    }

    @Override
    public List<SubjectDto> findAll(Pageable pageable) {
        return subjectRepository.findAll(pageable).getContent().stream()
                .map(SubjectDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void addSubjectToUser(long userId, long subjectId, int mark) {
        userRepository.findOne(userId).getSubjects().put(subjectRepository.findOne(subjectId), mark);
    }

    @Transactional
    @Override
    public void removeUserSubject(long userId, long subjectId) {
        userRepository.findOne(userId).getSubjects().remove(subjectRepository.findOne(subjectId));
    }

    @Override
    public void update(SubjectDto id) {

    }

    @Override
    public List<SubjectDto> findSubjectsByInventoryIds(List<Long> subjectIds) {
        return subjectRepository.findByInventoryIds(subjectIds).stream()
                .map(SubjectDto::new)
                .collect(Collectors.toList());
    }
}
