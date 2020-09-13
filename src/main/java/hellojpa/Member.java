package hellojpa;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;

@Entity
// @Table(name = "USER") // DB 테이블명이 다를 경우
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // GenerationType.AUTO 자동 방언
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

//    EnumType.ORDINAL: enum 순서를 데이터베이스에 저장
//    EnumType.STRING: enum 이름을 데이터베이스에 저장
    // 주의  : ORDINAL 사용 X
    @Enumerated(EnumType.STRING)
    private RoleType roleType;


    @Lob // 큰 용량
    private String description;
}
