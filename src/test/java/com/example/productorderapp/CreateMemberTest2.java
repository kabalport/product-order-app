package com.example.productorderapp;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateMemberTest2 {

    private CreateMember createMember;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("멤버를 생성한다.")
    void CreateMember(){
        // given
        final String name = "name";
        final String email = "email";

        final CreateMember.Request request = new CreateMember.Request(name,email);

        // when
        createMember.request(request);
        // then
        Approvals.verify(new CreatedUserExpectation(name,email));

}

    private record CreatedUserExpectation(String name, String email){}
    private MemberRepository memberRepository;
    private class CreateMember {



        public CreateMember() {


            // given
            final String name = "name";
            final String email = "email";


            final Member member = new Member(name,email);
            memberRepository.save(member);
        }




        public void request(Request request) {

        }


            public record Request(String name, String email) {


        }

        private class Member {
            private final String name;
            private final String email;

            public Member(String name, String email) {
                this.name = name;
                this.email = email;
            }
        }
    }

    private interface MemberRepository {
        void save(CreateMember.Member member);
    }
}
