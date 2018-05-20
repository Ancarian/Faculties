package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StatisticService {
    List<GroupDto> getTopGroupsByMark(Pageable pageable);

    List<GroupDto> getTopGroupsByAvgMark();

    List<UserDto> getEnrollUsers(long id);
}

