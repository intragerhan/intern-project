package com.intern.ambassador.service.impl;

import com.intern.ambassador.data.dto.ApplyDto;
import com.intern.ambassador.data.dto.ApplyResponseDto;
import com.intern.ambassador.data.entity.Apply;
import com.intern.ambassador.data.repository.ApplicationRepository;
import com.intern.ambassador.service.ApplyService;
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
@Import({ApplyServiceImpl.class})
public class ApplyServiceTest2 {

    @MockBean
    ApplicationRepository applicationRepository;

    @Autowired
    ApplyService applyService;

    @Test
    void getApplicationTest() {
        Apply givenApply = new Apply();
        givenApply.setAno(123L);
        givenApply.setReason("지원동기");
        givenApply.setFeedback("피드백을 받는다면");
        givenApply.setActivity("활동");
        givenApply.setAdvantage("장점");
        givenApply.setLastWord("마지막 한 마디");

        Mockito.when(applicationRepository.findById(123L))
                .thenReturn(Optional.of(givenApply));

        ApplyResponseDto applyResponseDto = applyService.getApplication(123L);

        assertEquals(applyResponseDto.getAno(), givenApply.getAno());
        assertEquals(applyResponseDto.getReason(), givenApply.getReason());
        assertEquals(applyResponseDto.getFeedback(), givenApply.getFeedback());
        assertEquals(applyResponseDto.getActivity(), givenApply.getActivity());
        assertEquals(applyResponseDto.getAdvantage(), givenApply.getAdvantage());
        assertEquals(applyResponseDto.getLastWord(), givenApply.getLastWord());

        verify(applicationRepository).findById(123L);
    }

    @Test
    void submitApplication() {
        Mockito.when(applicationRepository.save(any(Apply.class)))
                .then(returnsFirstArg());

        ApplyResponseDto applyResponseDto = applyService.submitApplication(
                new ApplyDto("지원동기", "피드백을 받는다면", "활동", "장점", "마지막 한 마디"));

        assertEquals(applyResponseDto.getReason(), "지원동기");
        assertEquals(applyResponseDto.getFeedback(), "피드백을 받는다면");
        assertEquals(applyResponseDto.getActivity(), "활동");
        assertEquals(applyResponseDto.getAdvantage(), "장점");
        assertEquals(applyResponseDto.getLastWord(), "마지막 한 마디");

        verify(applicationRepository).save(any());
    }
}
