package com.intern.ambassador.controller;

import com.intern.ambassador.data.dto.ApplyDto;
import com.intern.ambassador.data.dto.ApplyResponseDto;
import com.intern.ambassador.data.dto.ChangeContentsDto;
import com.intern.ambassador.service.ApplyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiImplicitParams({
        @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true,
                dataType = "String", paramType = "header")
})
@Slf4j
@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplyController {
    private final ApplyService applyService;

    /**
     * 지원자가 신청서 제출
     */
    @PostMapping
    public ResponseEntity<ApplyResponseDto> submitApplication(@RequestBody ApplyDto applyDto) {
        long currentTime = System.currentTimeMillis();
        ApplyResponseDto applyResponseDto = applyService.submitApplication(applyDto);
        log.info("[submitApplication] Response Time : {}ms", System.currentTimeMillis() - currentTime);
        return ResponseEntity.status(HttpStatus.OK).body(applyResponseDto);
    }
    /**
     * 지원자가 신청서 수정
     */
    @PutMapping
    public ResponseEntity<ApplyResponseDto> amendContents(
            @RequestBody ChangeContentsDto changeContentsDto) throws Exception {
        ApplyResponseDto applyResponseDto = applyService.changeContents(changeContentsDto);
        return ResponseEntity.status(HttpStatus.OK).body(applyResponseDto);
    }
    /**
     * 지원자가 신청서 확인
     */
    @GetMapping("{id}")
    public ResponseEntity<ApplyResponseDto> inspectApplication(@PathVariable("id") Long ano) {
        ApplyResponseDto applyResponseDto = applyService.getApplication(ano);
        return ResponseEntity.status(HttpStatus.OK).body(applyResponseDto);
    }

}
