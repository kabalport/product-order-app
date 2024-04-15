package com.example.productorderapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CreateMemberTest  {
    @Autowired
    private CreateMember createMember;
    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp(){
        createMember = new CreateMember();
    }

    private class CreateMemberFixture {
        private final String name;
        private final String email;

        public CreateMemberFixture(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }



    @Test
    @DisplayName("멤버를 생성합니다.")
    void createMember(){
        // given
        final String userId = "userId";
        final String email = "email";
        final MemberDTO.request request = new MemberDTO.request(userId, email);

        // when : 로직수행
        createMember.execute(request);

        // then : 기대한 결과값과 동일합니다.
        final Member member = memberRepository.findById(1L).get();
        assertNotNull(member, "결과 null 확인");
        assertEquals(member,new CreateMemberFixture("userId","email"),"예상과 결과가 동일한지 확인합니다.");
    }


class Member {
        private final String userId;
        private final String email;


        public Member(String userId, String email) {
            this.userId = userId;
            this.email = email;
        }
    }

    private class CreateMember {
        public Member execute(MemberDTO.request request) {

            final String userId = request.userId();
            final String email = request.email();

            final Member member = new Member(userId,email);
            memberRepository.save(member);
        }
    }

    private class MemberDTO {
        public static class request {
            String userId;
            String email;
            public request(String userId, String email) {
                this.userId = userId;
                this.email = email;
            }
        }
    }

    private interface MemberRepository extends JpaRepository<Member,Long> {
        Member save(MemberDTO.request member);
    }
}
