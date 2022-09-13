package com.intern.ambassador.service.impl;

import com.intern.ambassador.data.dto.ApplicationDto;
import com.intern.ambassador.data.dto.ApplicationResponseDto;
import com.intern.ambassador.data.entity.Application;
import com.intern.ambassador.data.repository.ApplicationRepository;
import com.intern.ambassador.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

/**
 * @MockBean 어노테이션을 사용한 테스트 환경 설정
 * SpringExtension 은 JUnit5의 Jupiter 테스트에 스프링 테스트 컨텍스트 프레임워크(Spring TestContextFramework)를 통합하는 역할 수행
 */
@ExtendWith(SpringExtension.class)
@Import({ApplicationServiceImpl.class})
public class ApplicationServiceTest2 {

    @MockBean
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationService applicationService;

    @Test
    void getApplicationTest() {
        Application givenApplication = new Application();
        givenApplication.setAno(123L);
        givenApplication.setReason("지원동기");
        givenApplication.setFeedback("피드백을 받는다면");
        givenApplication.setActivity("활동");
        givenApplication.setAdvantage("장점");
        givenApplication.setLastWord("마지막 한 마디");

        Mockito.when(applicationRepository.findById(123L))
                .thenReturn(Optional.of(givenApplication));

        ApplicationResponseDto applicationResponseDto = applicationService.getApplication(123L);

        assertEquals(applicationResponseDto.getAno(), givenApplication.getAno());
        assertEquals(applicationResponseDto.getReason(), givenApplication.getReason());
        assertEquals(applicationResponseDto.getFeedback(), givenApplication.getFeedback());
        assertEquals(applicationResponseDto.getActivity(), givenApplication.getActivity());
        assertEquals(applicationResponseDto.getAdvantage(), givenApplication.getAdvantage());
        assertEquals(applicationResponseDto.getLastWord(), givenApplication.getLastWord());

        verify(applicationRepository).findById(123L);
    }

    @Test
    void submitApplication() {
        Mockito.when(applicationRepository.save(any(Application.class)))
                .then(returnsFirstArg());

        ApplicationResponseDto applicationResponseDto = applicationService.submitApplication(
                new ApplicationDto("지원동기", "피드백을 받는다면", "활동", "장점", "마지막 한 마디"));

        assertEquals(applicationResponseDto.getReason(), "지원동기");
        assertEquals(applicationResponseDto.getFeedback(), "피드백을 받는다면");
        assertEquals(applicationResponseDto.getActivity(), "활동");
        assertEquals(applicationResponseDto.getAdvantage(), "장점");
        assertEquals(applicationResponseDto.getLastWord(), "마지막 한 마디");

        verify(applicationRepository).save(any());
    }
}
