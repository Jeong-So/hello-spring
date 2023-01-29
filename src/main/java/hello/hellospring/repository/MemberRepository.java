package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
//  Optional<> : findById 나 findByName 함수 사용 시 NULL일 수 있음
//  요즘에는 NULL값을 Optional로 감싸서 반환하는 걸 선호
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
//  findAll : 지금까지 저장된 모든 회원 리스트 반환
    List<Member> findAll();

}
