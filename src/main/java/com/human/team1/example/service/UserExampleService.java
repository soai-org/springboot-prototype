package com.human.team1.example.service;

import com.human.team1.example.mapper.UserExampleMapper;
import com.human.team1.example.domain.UserExampleVO;
import com.human.team1.example.dto.UserExampleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service    // @Configuration : 이 클래스가 스프링 Service 파일임을 나타냄
@RequiredArgsConstructor
public class UserExampleService {
    private final UserExampleMapper userMapper;

    public List<UserExampleVO> getAllUsers() {
        List<UserExampleDTO> dtoList = userMapper.selectAllUsers();
        return dtoList.stream() // dtoList를 Stream으로 변환 → 요소들을 하나씩 처리할 수 있게 됨
                .map(dto -> new UserExampleVO(dto.getId(),
                                                            dto.getPassword(),
                                                            dto.getName(),
                                                            dto.getAge()
                ))
                .collect(Collectors.toList());  // collect() : 변환된 Stream을 다시 List로 모아서 반환
    }

    public UserExampleVO getUserById(String id) {
        UserExampleDTO dto = userMapper.selectUserById(id);
        return new UserExampleVO(dto.getId(), dto.getPassword(), dto.getName(), dto.getAge());
    }

    public UserExampleVO createUser(UserExampleVO user) {
        UserExampleDTO dto = new UserExampleDTO(user.getId(), user.getPassword(), user.getName(), user.getAge());
        userMapper.insertUser(dto);
        return user;
    }
}