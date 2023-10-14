package mvc1servlet.servlet.domain.Member;

import mvc1servlet.servlet.domain.member.Member;
import mvc1servlet.servlet.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRespositoryTest {


    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void aterEach() {
        memberRepository.clearStore(); //test 끝날때마다 테스트 초기화
    }


    @Test
    void save() {
        //given 이런게 주어졌을 때
        Member member = new Member("hello", 20);
        //when 이런게 실행 됐을 때
        Member saveMember = memberRepository.save(member);
        //then 결과는 이래야 해
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isEqualTo(saveMember);
    }
    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result =memberRepository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1,member2);
    }
}