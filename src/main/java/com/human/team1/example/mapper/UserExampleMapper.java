package com.human.team1.example.mapper;

import com.human.team1.example.dto.UserExampleDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper // @Mapper : 이 클래스가 스프링 Mapper 파일임을 나타냄
public interface UserExampleMapper {
    List<UserExampleDTO> selectAllUsers();
    UserExampleDTO selectUserById(@Param("id") String id);
    int insertUser(UserExampleDTO user);
}