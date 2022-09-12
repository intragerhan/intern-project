package com.intern.ambassador.controller;

import com.intern.ambassador.data.dto.ApplicationDto;
import com.intern.ambassador.data.dto.ApplicationResponseDto;
import com.intern.ambassador.data.dto.ChangeContentsDto;
import com.intern.ambassador.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/application")
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    /** 지원자가 신청서 제출 */
    @PostMapping
    public ResponseEntity<ApplicationResponseDto> createApplication(@RequestBody ApplicationDto applicationDto) {
        ApplicationResponseDto applicationResponseDto = applicationService.submitApplication(applicationDto);
        return ResponseEntity.status(HttpStatus.OK).body(applicationResponseDto);
    }
    /** 지원자가 신청서 수정 */
    @PutMapping
    public ResponseEntity<ApplicationResponseDto> amendContents(
            @RequestBody ChangeContentsDto changeContentsDto) throws Exception {
        ApplicationResponseDto applicationResponseDto = applicationService.changeContents(changeContentsDto);
        return ResponseEntity.status(HttpStatus.OK).body(applicationResponseDto);
    }

    /** 관리자가 신청서 확인 */
    @GetMapping
    public ResponseEntity<ApplicationResponseDto> inspectApplication(Long ano) {
        ApplicationResponseDto applicationResponseDto = applicationService.getApplication(ano);
        return ResponseEntity.status(HttpStatus.OK).body(applicationResponseDto);
    }
    /** 관리자가 신청서 삭제 */
    @DeleteMapping
    public ResponseEntity<String> deleteApplication(Long ano) throws Exception {
        applicationService.deleteApplication(ano);
        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습니다.");
    }
}
