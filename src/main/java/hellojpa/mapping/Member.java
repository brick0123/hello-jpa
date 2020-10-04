package hellojpa.mapping;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // JPA에게 관계를 알려줘야함
    @ManyToOne(fetch = FetchType.EAGER) // Team 조회가 많을때 실무에서는 즉시 로딩 사용 금지!!
    //( fetch = FetchType.LAZY) // 지연로딩 비즈니스 로직에서 Team을 거의 호출하지 않는 경우
    // @ManyToOne, @OneToOne은 기본이 즉시 로딩
    // @OneToMany, @ManyToMany는 기본이 지연 로딩
    @JoinColumn(name = "TEAM_ID")
    private Team team;

//    @OneToOne
//    @JoinColumn(name = "LOCKER_ID")
//    private Locker locker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void chageTeam(Team team) {
        this.team = team;

        team.getMembers().add(this); // 양방향 추가 코드
    }
}
