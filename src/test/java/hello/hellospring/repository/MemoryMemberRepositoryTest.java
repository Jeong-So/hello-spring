package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

//  테스트는 서로 순서와 상관없이 (=의존관계 없이) 설계 필요, 하나의 테스트가 끝날때마다 레포지토리 클리어 필요
//  테스트가 끝날 때마다 repository를 깔끔하게 지워주는 코드 필요
//  그래야 순서에 상관없이 제대로 테스트가 됨
//  @AfterEach : 함수(메소드)가 끝날때마다 동작하는 콜백
//  save 끝나고 동작 findByName 끝나고 동작
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // Optional에서 값을 꺼낼 때는 .get() 사용하여 꺼냄
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result = " + (result == member));

        Assertions.assertEquals(result, member);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }

}
