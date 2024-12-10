package com.project.survey.application.command.survey;

import lombok.*;
import java.time.LocalDateTime;

import com.project.survey.adapter.in.web.dto.response.survey.SurveyResponseDto;
import com.project.survey.adapter.out.persistence.entity.constant.SurveyType;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class CreateSurveyCommand {
    private Long memberId;
    private String title;
    private SurveyType type;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;
    private String deleteYn;
    
    // factory method
    public static CreateSurveyCommand of(SurveyResponseDto surveyResponseDto) {
        return CreateSurveyCommand.builder()
                .memberId(surveyResponseDto.getMemberId())
                .title(surveyResponseDto.getTitle())
                .type(surveyResponseDto.getType())
                .perPoint(surveyResponseDto.getPerPoint())
                .limitSubmit(surveyResponseDto.getLimitSubmit())
                .startdate(surveyResponseDto.getStartdate())
                .enddate(surveyResponseDto.getEnddate())
                .deleteYn(surveyResponseDto.getDeleteYn())
                .build();
    }
}