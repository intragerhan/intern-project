package com.intern.ambassador.controller;

import com.google.gson.Gson;
import com.intern.ambassador.data.dto.ApplyDto;
import com.intern.ambassador.data.dto.ApplyResponseDto;
import com.intern.ambassador.service.impl.ApplyServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 슬라이스 테스트
 * @WebMvcTest 을 사용한 테스트이며, 단위 테스트와 통합 테스트의 중간 개념임
 * 레이어드 아키텍처를 기준으로 각 레이어별로 나누어 테스트를 진행
 */
@WebMvcTest(ApplyController.class)
public class ApplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ApplyServiceImpl applicationService;

    @Test
    @DisplayName("신청서 데이터 가져오기 테스트")
    void getApplicationTest() throws Exception {
        given(applicationService.getApplication(123L)).willReturn(new ApplyResponseDto(123L,
                "지원동기", "피드백을 받는다면", "활동", "장점", "마지막 한 마디"));

        String ano = "123";

        mockMvc.perform(get("/application?ano=" + ano))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ano").exists())
                .andExpect(jsonPath("$.reason").exists())
                .andExpect(jsonPath("$.feedback").exists())
                .andExpect(jsonPath("$.activity").exists())
                .andExpect(jsonPath("$.advantage").exists())
                .andExpect(jsonPath("$.lastWord").exists())
                .andDo(print());

        verify(applicationService).getApplication(123L);
    }

    @Test
    @DisplayName("지원서 더미데이터 제출 테스트")
    void submitApplicationTest() throws Exception {
        // Mock 객체에서 특정 메서드가 실행되는 경우 실제 Return을 줄 수 없기 때문에 아래와 같이 가정 사항을 만들어줌
        given(applicationService.submitApplication(new ApplyDto(
                "지원동기", "피드백을 받는다면", "활동", "장점", "마지막 한 마디")))
                .willReturn(new ApplyResponseDto(12L, "지원동기", "피드백을 받는다면", "활동", "장점", "마지막 한 마디"));

        ApplyDto applyDto = ApplyDto.builder()
                .reason("지원동기")
                .feedback("피드백을 받는다면")
                .activity("활동")
                .advantage("장점")
                .lastWord("마지막 한 마디")
                .build();

        Gson gson = new Gson();
        String contents = gson.toJson(applyDto);

        mockMvc.perform(post("/application")
                .content(contents)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ano").exists())
                .andExpect(jsonPath("$.reason").exists())
                .andExpect(jsonPath("$.feedback").exists())
                .andExpect(jsonPath("$.activity").exists())
                .andExpect(jsonPath("$.advantage").exists())
                .andExpect(jsonPath("$.lastWord").exists())
                .andDo(print());

        verify(applicationService).submitApplication(new ApplyDto(
                "지원동기", "피드백을 받는다면", "활동", "장점", "마지막 한 마디"));
    }
}
