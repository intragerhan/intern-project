package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ApplicationRepositoryTestByH2 {
    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Test
    void saveTest() {
        // given
        Application application = new Application();
        application.setReason("지원동기");
        application.setFeedback("피드백을 받는다면");
        application.setActivity("활동");
        application.setAdvantage("장점");
        application.setLastWord("마지막 한 마디");

        // when
        Application submitApplication = applicationRepository.save(application);

        // then
        assertEquals(application.getReason(), submitApplication.getReason());
        assertEquals(application.getFeedback(), submitApplication.getFeedback());
        assertEquals(application.getActivity(), submitApplication.getActivity());
        assertEquals(application.getAdvantage(), submitApplication.getAdvantage());
        assertEquals(application.getLastWord(), submitApplication.getLastWord());
    }

    @Test
    void selectTest() {
        // given
        Application application = new Application();
        application.setReason("지원동기");
        application.setFeedback("피드백을 받는다면");
        application.setActivity("활동");
        application.setAdvantage("장점");
        application.setLastWord("마지막 한 마디");

        Application submittedApplication = applicationRepository.saveAndFlush(application);

        // when
        Application foundApplication = applicationRepository.findById(submittedApplication.getAno()).get();

        // then
        assertEquals(foundApplication.getReason(), submittedApplication.getReason());
        assertEquals(foundApplication.getFeedback(), submittedApplication.getFeedback());
        assertEquals(foundApplication.getActivity(), submittedApplication.getActivity());
        assertEquals(foundApplication.getAdvantage(), submittedApplication.getAdvantage());
        assertEquals(foundApplication.getLastWord(), submittedApplication.getLastWord());
    }
}
