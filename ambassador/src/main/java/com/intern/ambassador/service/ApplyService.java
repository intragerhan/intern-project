package com.intern.ambassador.service;

import com.intern.ambassador.data.dto.ApplyDto;
import com.intern.ambassador.data.dto.ApplyResponseDto;
import com.intern.ambassador.data.dto.ChangeContentsDto;
import com.intern.ambassador.data.entity.Apply;

public interface ApplyService {
    ApplyResponseDto getApplication(Long ano);
    ApplyResponseDto submitApplication(ApplyDto applyDto);
    ApplyResponseDto changeContents(ChangeContentsDto changeContentsDto) throws Exception;
    void deleteApplication(Long ano) throws Exception;

    default ApplyResponseDto getApplicationResponseDto(Apply apply) {
        ApplyResponseDto applyResponseDto = new ApplyResponseDto();

        applyResponseDto.setAno(apply.getAno());
        applyResponseDto.setReason(apply.getReason());
        applyResponseDto.setFeedback(apply.getFeedback());
        applyResponseDto.setActivity(apply.getActivity());
        applyResponseDto.setAdvantage(apply.getAdvantage());
        applyResponseDto.setLastWord(apply.getLastWord());

        return applyResponseDto;
    }

    default Apply getApplicationEntity(ApplyDto applyDto) {
        Apply apply = new Apply();

        apply.setReason(applyDto.getReason());
        apply.setFeedback(applyDto.getFeedback());
        apply.setActivity(applyDto.getActivity());
        apply.setAdvantage(applyDto.getAdvantage());
        apply.setLastWord(applyDto.getLastWord());

        return apply;
    }
}
