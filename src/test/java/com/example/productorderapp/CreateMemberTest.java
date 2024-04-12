package com.example.productorderapp;

import com.spun.util.persistence.ExecutableCommand;
import org.approvaltests.Approvals;
import org.approvaltests.approvers.ApprovalApprover;
import org.approvaltests.core.ApprovalWriter;
import org.approvaltests.core.Verifiable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Objects;

public class CreateMemberTest  {
    @Test
    @DisplayName("멤버를 생성한다.")
    void createMember(){
        // then
        Approvals.verify(new CreatedUserExpectation("홍길동","qwer@naver.com","Bronze"));
    }

    private static final class CreatedUserExpectation {
        private final String name;
        private final String email;
        private final String level;

        private CreatedUserExpectation(String name, String email, String level) {
            this.name = name;
            this.email = email;
            this.level = level;
        }

        public String name() {
            return name;
        }

        public String email() {
            return email;
        }

        public String level() {
            return level;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (CreatedUserExpectation) obj;
            return Objects.equals(this.name, that.name) &&
                    Objects.equals(this.email, that.email) &&
                    Objects.equals(this.level, that.level);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, email, level);
        }

        @Override
        public String toString() {
            return "CreatedUserExpectation[" +
                    "name=" + name + ", " +
                    "email=" + email + ", " +
                    "level=" + level + ']';
        }
    }
}
