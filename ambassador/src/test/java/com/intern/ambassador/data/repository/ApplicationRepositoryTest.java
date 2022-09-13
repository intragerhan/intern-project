package com.intern.ambassador.data.repository;

import com.intern.ambassador.data.entity.Application;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void save() {
        Application application = new Application();
        application.setReason("지원동기");
        application.setFeedback("피드백을 받는다면");
        application.setActivity("활동");
        application.setAdvantage("장점");
        application.setLastWord("마지막 한 마디");

        Application submittedApplication = applicationRepository.save(application);

        assertEquals(application.getReason(), submittedApplication.getReason());
        assertEquals(application.getFeedback(), submittedApplication.getFeedback());
        assertEquals(application.getActivity(), submittedApplication.getActivity());
        assertEquals(application.getAdvantage(), submittedApplication.getAdvantage());
        assertEquals(application.getLastWord(), submittedApplication.getLastWord());
    }
}
