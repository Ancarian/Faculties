package dev.chermenin.service.api.impl;

import dev.chermenin.dao.SubjectRepository;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.dao.dto.subject.SubjectDto;
import dev.chermenin.dao.dto.subject.SubjectScoreDto;
import dev.chermenin.model.impl.Subject;
import dev.chermenin.model.impl.User;
import dev.chermenin.service.api.SubjectService;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void updateUserSubjects(List<SubjectScoreDto> subjectScoreDtoList, Long userId) {
        User user = userRepository.findAllUserSubjects(userId).orElseThrow(() ->
                new NotFoundException("error.user.NotExistsId")
        );
        Map<Subject, Integer> newUserSubjects = subjectScoreDtoList.stream()
                .collect(Collectors.toMap(SubjectScoreDto::getSubject, SubjectScoreDto::getScore));
        user.getSubjects().keySet().removeIf(key -> !newUserSubjects.keySet().contains(key));
        user.getSubjects().putAll(newUserSubjects);
        userRepository.flush();
    }

    @Override
    public void save(List<SubjectDto> subjectsDto) {
        List<Subject> subjects = subjectsDto.stream().map((o) -> {
                    Subject subject = new Subject();
                    subject.setSubject(o.getSubject());
                    return subject;
                }
        ).collect(Collectors.toList());
        subjectRepository.saveAll(subjects);
    }

    @Override
    public void save(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setSubject(subjectDto.getSubject());
        subjectRepository.save(subject);
    }

    @Override
    public List<SubjectDto> findAll() {
        return subjectRepository.findAll().stream().
                map(SubjectDto::new).
                collect(Collectors.toList());
    }
}
