package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.Application;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ApplicationRepositoryTest2 {
    @Autowired
    ApplicationRepository applicationRepository;

    @Test
    public void basicCRUDTest() {
        /* Create */
        // given
        Application givenApplication = Application.builder()
                .reason("지원동기")
                .feedback("피드백을 받는다면")
                .activity("활동")
                .advantage("장점")
                .lastWord("마지막 한 마디")
                .build();

        // when
        Application submittedApplication = applicationRepository.save(givenApplication);

        // then
        assertThat(submittedApplication.getAno()).isEqualTo(givenApplication.getAno());
        assertThat(submittedApplication.getReason()).isEqualTo(givenApplication.getReason());
        assertThat(submittedApplication.getFeedback()).isEqualTo(givenApplication.getFeedback());
        assertThat(submittedApplication.getActivity()).isEqualTo(givenApplication.getActivity());
        assertThat(submittedApplication.getAdvantage()).isEqualTo(givenApplication.getAdvantage());
        assertThat(submittedApplication.getLastWord()).isEqualTo(givenApplication.getLastWord());

        /* Read */
        // when
        Application selectedApplication = applicationRepository.findById(submittedApplication.getAno())
                .orElseThrow(RuntimeException::new);

        // then
        assertThat(selectedApplication.getAno()).isEqualTo(givenApplication.getAno());
        assertThat(selectedApplication.getReason()).isEqualTo(givenApplication.getReason());
        assertThat(selectedApplication.getFeedback()).isEqualTo(givenApplication.getFeedback());
        assertThat(selectedApplication.getActivity()).isEqualTo(givenApplication.getActivity());
        assertThat(selectedApplication.getAdvantage()).isEqualTo(givenApplication.getAdvantage());
        assertThat(selectedApplication.getLastWord()).isEqualTo(givenApplication.getLastWord());

        /* Update */
        // when
        Application foundApplication = applicationRepository.findById(selectedApplication.getAno())
                .orElseThrow(RuntimeException::new);
        
        foundApplication.setReason("지원동기123");
        foundApplication.setFeedback("피드백을 받는다면123");
        foundApplication.setActivity("활동123");
        foundApplication.setAdvantage("장점123");
        foundApplication.setLastWord("마지막 한 마디123");

        Application updatedApplication = applicationRepository.save(foundApplication);

        // then
        assertEquals(updatedApplication.getReason(), "지원동기123");
        assertEquals(updatedApplication.getFeedback(), "피드백을 받는다면123");
        assertEquals(updatedApplication.getActivity(), "활동123");
        assertEquals(updatedApplication.getAdvantage(), "장점123");
        assertEquals(updatedApplication.getLastWord(), "마지막 한 마디123");

        /* Delete */
        // when
        applicationRepository.delete(updatedApplication);

        // then
        assertFalse(applicationRepository.findById(selectedApplication.getAno()).isPresent());
    }
}
