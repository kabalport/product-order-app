package com.example.productorderapp;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateMemberTest  {
    // pojo 개발합니다.
    // innerclass 로 개발합니다. 개발이 끝난뒤 파일을 이동시킬 것입니다.
    // pk는 id로 설정합니다. auto increment입니다.
    // uuid라는 칼럼도 만듭니다. 이 컬럼은 String 타입입니다. 그리고 자바로 생성합니다. 그리고 unique key로 설정합니다.
    // name이라는 칼럼도 만듭니다. String 타입입니다. null로 설정합니다.
    // email이라는 칼럼도 만듭니다. String 타입입니다. null로 설정합니다.

    @Test
    @DisplayName("멤버를 생성합니다.")
    void createMember(){



        // then: 먼저 then 만듭니다.
        assertEquals(1,1);
    }

}
