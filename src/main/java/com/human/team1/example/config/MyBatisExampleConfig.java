package com.human.team1.example.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration  // @Configuration : 이 클래스가 스프링 설정 파일임을 알려주는 어노테이션
@MapperScan(basePackages = "com.human.team1.example.mapper")    // @MapperScan : MyBatis의 Mapper 인터페이스들이 들어있는 패키지를 지정 -> 실행 파일에 다는 경우도 있음
public class MyBatisExampleConfig {

    @Bean   // @Bean : 스프링이 관리하는 Bean 객체로 등록됨 (여기서는 SqlSessionFactory를 등록) -> @Bean이 붙은 메서드는 스프링이 자동으로 싱글톤으로 등록하고 필요할 때마다 꺼내 씀.
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // SqlSessionFactoryBean : MyBatis의 SqlSessionFactory를 만들어주는 Spring 전용 클래스
        // DB랑 MyBatis를 연결해주는 역할 Mapper의 SQL문 실행과 VO/DTO 객체로의 변환을 담당함
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);   // DB 연결 정보(DataSource) 설정
        
        // 매퍼 XML 파일 위치 설정
        sessionFactory.setMapperLocations(
            new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml")
        );
        
        // 타입 별칭 패키지 설정
        // VO, DTO 클래스들의 전체 경로를 적지 않고 간단한 클래스명으로 매퍼 XML에서 사용 가능
        sessionFactory.setTypeAliasesPackage("com.human.team1.example.dto,com.human.team1.example.domain");
        
        // MyBatis 설정
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // DB 컬럼명(스네이크 케이스, ex: user_id) → 자바 필드명(카멜 케이스, ex: userId) 자동 매핑
        configuration.setMapUnderscoreToCamelCase(true);
        // 실행되는 SQL을 콘솔에 출력 (디버깅용)
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        // 위 설정들을 sessionFactory에 적용
        sessionFactory.setConfiguration(configuration);
        
        return sessionFactory.getObject();
    }
} 