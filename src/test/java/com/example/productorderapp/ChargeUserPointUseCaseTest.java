package com.example.productorderapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;




class ChargeUserPointUseCaseTest {
    private ChargeBalanceUseCase chargeBalanceUseCase;
    private AccountValidator accountValidator; // 이는 여전히 인터페이스일 수 있으며, 더 간단한 검증 로직을 포함할 수 있습니다.
    private SimpleMemberReader memberReader;
    private SimpleAccountManager accountManager;


    public class SimpleMemberReader implements MemberReader {
        private Map<String, Member> members = new HashMap<>();

        public SimpleMemberReader() {
            members.put("user0", new Member("user0"));
        }

        @Override
        public Member read(String userId) {
            return members.get(userId);
        }
    }

    public class SimpleAccountManager implements AccountManager, AccountReader {
        private Map<String, Account> accounts = new HashMap<>();

        public SimpleAccountManager() {
            accounts.put("user0", new Account("user0", 1000));
        }

        @Override
        public Account charge(Account account) {
            if (account.getBalance() < 0) {
                throw new AccountException("Negative balance not allowed");
            }
            account.setBalance(account.getBalance() + 500); // 가정: 항상 500만큼 충전
            return account;
        }

        @Override
        public Account read(Member member) {
            return accounts.get(member.getUserId());
        }
    }

    @BeforeEach
    void setUp() {
        accountValidator = new SimpleAccountValidator(); // 이는 단순한 로직을 포함할 수 있습니다.
        memberReader = new SimpleMemberReader();
        accountManager = new SimpleAccountManager();
        chargeBalanceUseCase = new ChargeBalanceUseCase(accountValidator, memberReader, accountManager);
    }

    @Test
    @DisplayName("음수 금액을 충전하려 할 때 예외 발생 확인")
    void chargeNegativeAmount() {
        var minusRequest = AccountFixture.addNegativeBalanceRequest();

        Exception exception = assertThrows(AccountException.class, () -> {
            chargeBalanceUseCase.execute(minusRequest);
        });
        assertEquals("Negative balance not allowed", exception.getMessage());
    }

    @Test
    @DisplayName("잔액충전로직검증_충전금_1000원을 충전한경우")
    void success1() {
        var request = AccountFixture.addAccountRequest();
        Account result = chargeBalanceUseCase.execute(request);

        assertNotNull(result, "결과 null 확인");
        assertEquals(1500, result.getBalance(), "예상잔고와 결과잔고 동일한지 확인");
    }



    interface MemberReader {
        Member read(String userId);
    }

    interface AccountManager {
        Account charge(Account account);
    }


    interface AccountReader {
        Account read(Member member);
    }

    class Member {
        private String userId;

        public Member(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }
    }

    class Account {
        private String userId;
        private int balance;

        public Account(String userId, int balance) {
            this.userId = userId;
            this.balance = balance;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public String getUserId() {
            return userId;
        }
    }

    class AccountException extends RuntimeException {
        public AccountException(String message) {
            super(message);
        }
    }

    class ChargeBalanceUseCase {
        private AccountValidator validator;
        private MemberReader memberReader;
        private AccountManager accountManager;
        private AccountReader accountReader;

        public ChargeBalanceUseCase(AccountValidator validator, MemberReader memberReader, AccountManager accountManager) {
            this.validator = validator;
            this.memberReader = memberReader;
            this.accountManager = accountManager;
        }

        public Account execute(AccountDTO.Request request) throws AccountException {
            Member member = memberReader.read(request.getUserId());
            Account account = accountReader.read(member);
            if (request.getAmount() < 0 && !validator.validate(request.getAmount())) {
                throw new AccountException("Negative balance not allowed");
            }
            account.setBalance(account.getBalance() + request.getAmount());
            return accountManager.charge(account);
        }
    }

    interface AccountValidator {
        boolean validate(int amount);
    }

    public static class AccountFixture {
        public static AccountDTO.Request addNegativeBalanceRequest() {
            return new AccountDTO.Request("user0", -900000);
        }

        public static AccountDTO.Request addAccountRequest() {
            return new AccountDTO.Request("user0", 1000);
        }
    }

    class AccountDTO {
        static class Request {
            private String userId;
            private int amount;

            public Request(String userId, int amount) {
                this.userId = userId;
                this.amount = amount;
            }

            public String getUserId() {
                return userId;
            }

            public int getAmount() {
                return amount;
            }
        }
    }

    public class SimpleAccountValidator implements AccountValidator {
        @Override
        public boolean validate(int amount) {
            return amount >= 0;
        }
    }



}
