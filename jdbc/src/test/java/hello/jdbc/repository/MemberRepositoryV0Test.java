package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // save
        Member member = new Member("memberV100", 10000);
        repository.save(member);

        // findById
        Member findMemeber = repository.findById(member.getMemberId());
        log.info("findMember = {}", findMemeber);
        assertThat(findMemeber).isEqualTo(member);

        // update : money : 10000 -> 20000
        repository.update(member.getMemberId(), 20000);
        Member updatedMemeber = repository.findById(member.getMemberId());
        assertThat(updatedMemeber.getMoney()).isEqualTo(20000);

        // delete
        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

    }
}