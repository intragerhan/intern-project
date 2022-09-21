package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.Apply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApplyRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void save() {
        Apply apply = new Apply();
        apply.setReason("지원동기");
        apply.setFeedback("피드백을 받는다면");
        apply.setActivity("활동");
        apply.setAdvantage("장점");
        apply.setLastWord("마지막 한 마디");

        Apply submittedApply = applicationRepository.save(apply);

        assertEquals(apply.getReason(), submittedApply.getReason());
        assertEquals(apply.getFeedback(), submittedApply.getFeedback());
        assertEquals(apply.getActivity(), submittedApply.getActivity());
        assertEquals(apply.getAdvantage(), submittedApply.getAdvantage());
        assertEquals(apply.getLastWord(), submittedApply.getLastWord());
    }
}
