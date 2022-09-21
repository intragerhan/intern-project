package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.Apply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ApplyRepositoryTestByH2 {
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Test
    void saveTest() {
        // given
        Apply apply = new Apply();
        apply.setReason("지원동기");
        apply.setFeedback("피드백을 받는다면");
        apply.setActivity("활동");
        apply.setAdvantage("장점");
        apply.setLastWord("마지막 한 마디");

        // when
        Apply submitApply = applicationRepository.save(apply);

        // then
        assertEquals(apply.getReason(), submitApply.getReason());
        assertEquals(apply.getFeedback(), submitApply.getFeedback());
        assertEquals(apply.getActivity(), submitApply.getActivity());
        assertEquals(apply.getAdvantage(), submitApply.getAdvantage());
        assertEquals(apply.getLastWord(), submitApply.getLastWord());
    }

    @Test
    void selectTest() {
        // given
        Apply apply = new Apply();
        apply.setReason("지원동기");
        apply.setFeedback("피드백을 받는다면");
        apply.setActivity("활동");
        apply.setAdvantage("장점");
        apply.setLastWord("마지막 한 마디");

        Apply submittedApply = applicationRepository.saveAndFlush(apply);

        // when
        Apply foundApply = applicationRepository.findById(submittedApply.getAno()).get();

        // then
        assertEquals(foundApply.getReason(), submittedApply.getReason());
        assertEquals(foundApply.getFeedback(), submittedApply.getFeedback());
        assertEquals(foundApply.getActivity(), submittedApply.getActivity());
        assertEquals(foundApply.getAdvantage(), submittedApply.getAdvantage());
        assertEquals(foundApply.getLastWord(), submittedApply.getLastWord());
    }
}
