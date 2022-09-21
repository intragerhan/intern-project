package com.intern.ambassador.service.impl;

import com.intern.ambassador.data.dto.ApplyDto;
import com.intern.ambassador.data.dto.ApplyResponseDto;
import com.intern.ambassador.data.dto.ChangeContentsDto;
import com.intern.ambassador.data.entity.Apply;
import com.intern.ambassador.data.repository.ApplicationRepository;
import com.intern.ambassador.service.ApplyService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplyServiceImpl implements ApplyService {
    private final Logger LOGGER = LoggerFactory.getLogger(ApplyServiceImpl.class);
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional(readOnly = true)
    public ApplyResponseDto getApplication(Long ano) {
        LOGGER.info("[getApply] input number : {}", ano);
        Apply apply = applicationRepository.findById(ano).get();
        LOGGER.info("[getApply] input apply : {}", apply);
        ApplyResponseDto applyResponseDto = getApplicationResponseDto(apply);
        return applyResponseDto;
    }

    @Override
    public ApplyResponseDto submitApplication(ApplyDto applyDto) {
        LOGGER.info("[submitApplication] applyDto : {}", applyDto.toString());
        Apply apply = getApplicationEntity(applyDto);
        Apply savedApply = applicationRepository.save(apply);
        ApplyResponseDto applyResponseDto = getApplicationResponseDto(savedApply);
        LOGGER.info("[submitApplication] applyResponseDto : {}", applyResponseDto);
        return applyResponseDto;
    }

    @Override
    public ApplyResponseDto changeContents(ChangeContentsDto changeContentsDto) {
        Apply foundApply = applicationRepository.findById(changeContentsDto.getAno()).get();
        getApplicationResponseDto(foundApply);
        Apply changedContents = applicationRepository.save(foundApply);

        ApplyResponseDto applyResponseDto = getApplicationResponseDto(changedContents);
        return applyResponseDto;
    }

    @Override
    public void deleteApplication(Long ano) throws Exception {
        applicationRepository.deleteById(ano);
    }
}
