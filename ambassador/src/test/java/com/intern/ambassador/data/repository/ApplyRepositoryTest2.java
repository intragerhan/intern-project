package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.Apply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ApplyRepositoryTest2 {
    @Autowired
    ApplicationRepository applicationRepository;

    @Test
    public void basicCRUDTest() {
        /* Create */
        // given
        Apply givenApply = Apply.builder()
                .reason("지원동기")
                .feedback("피드백을 받는다면")
                .activity("활동")
                .advantage("장점")
                .lastWord("마지막 한 마디")
                .build();

        // when
        Apply submittedApply = applicationRepository.save(givenApply);

        // then
        assertThat(submittedApply.getAno()).isEqualTo(givenApply.getAno());
        assertThat(submittedApply.getReason()).isEqualTo(givenApply.getReason());
        assertThat(submittedApply.getFeedback()).isEqualTo(givenApply.getFeedback());
        assertThat(submittedApply.getActivity()).isEqualTo(givenApply.getActivity());
        assertThat(submittedApply.getAdvantage()).isEqualTo(givenApply.getAdvantage());
        assertThat(submittedApply.getLastWord()).isEqualTo(givenApply.getLastWord());

        /* Read */
        // when
        Apply selectedApply = applicationRepository.findById(submittedApply.getAno())
                .orElseThrow(RuntimeException::new);

        // then
        assertThat(selectedApply.getAno()).isEqualTo(givenApply.getAno());
        assertThat(selectedApply.getReason()).isEqualTo(givenApply.getReason());
        assertThat(selectedApply.getFeedback()).isEqualTo(givenApply.getFeedback());
        assertThat(selectedApply.getActivity()).isEqualTo(givenApply.getActivity());
        assertThat(selectedApply.getAdvantage()).isEqualTo(givenApply.getAdvantage());
        assertThat(selectedApply.getLastWord()).isEqualTo(givenApply.getLastWord());

        /* Update */
        // when
        Apply foundApply = applicationRepository.findById(selectedApply.getAno())
                .orElseThrow(RuntimeException::new);
        
        foundApply.setReason("지원동기123");
        foundApply.setFeedback("피드백을 받는다면123");
        foundApply.setActivity("활동123");
        foundApply.setAdvantage("장점123");
        foundApply.setLastWord("마지막 한 마디123");

        Apply updatedApply = applicationRepository.save(foundApply);

        // then
        assertEquals(updatedApply.getReason(), "지원동기123");
        assertEquals(updatedApply.getFeedback(), "피드백을 받는다면123");
        assertEquals(updatedApply.getActivity(), "활동123");
        assertEquals(updatedApply.getAdvantage(), "장점123");
        assertEquals(updatedApply.getLastWord(), "마지막 한 마디123");

        /* Delete */
        // when
        applicationRepository.delete(updatedApply);

        // then
        assertFalse(applicationRepository.findById(selectedApply.getAno()).isPresent());
    }
}
