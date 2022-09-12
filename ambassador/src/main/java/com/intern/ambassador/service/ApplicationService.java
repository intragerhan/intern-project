package com.intern.ambassador.service;

import com.intern.ambassador.data.dto.ApplicationDto;
import com.intern.ambassador.data.dto.ApplicationResponseDto;
import com.intern.ambassador.data.dto.ChangeContentsDto;
import com.intern.ambassador.data.entity.Application;

public interface ApplicationService {
    ApplicationResponseDto getApplication(Long ano);
    ApplicationResponseDto submitApplication(ApplicationDto applicationDto);
    ApplicationResponseDto changeContents(ChangeContentsDto changeContentsDto) throws Exception;
    void deleteApplication(Long ano) throws Exception;

    default ApplicationResponseDto getApplicationResponseDto(Application application) {
        ApplicationResponseDto applicationResponseDto = new ApplicationResponseDto();

        applicationResponseDto.setAno(application.getAno());
        applicationResponseDto.setReason(application.getReason());
        applicationResponseDto.setFeedback(application.getFeedback());
        applicationResponseDto.setActivity(application.getActivity());
        applicationResponseDto.setAdvantage(application.getAdvantage());
        applicationResponseDto.setLastWord(application.getLastWord());

        return applicationResponseDto;
    }

    default Application getApplicationEntity(ApplicationDto applicationDto) {
        Application application = new Application();

        application.setReason(applicationDto.getReason());
        application.setFeedback(applicationDto.getFeedback());
        application.setActivity(applicationDto.getActivity());
        application.setAdvantage(application.getAdvantage());
        application.setLastWord(application.getLastWord());

        return application;
    }
}
