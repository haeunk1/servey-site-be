package com.project.servey.application.command.servey;

import java.time.LocalDateTime;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyResponseDto;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UpdateServeyCommand {
    private Long serveyId;
    private String title;
    private int type;
    private int category;
    private int perPoint;
    private int limitSubmit;
    private LocalDateTime startdate;
    private LocalDateTime enddate;

    // factory method
    public static UpdateServeyCommand of(ServeyResponseDto serveyResponseDto) {
        return UpdateServeyCommand.builder()
                .serveyId(serveyResponseDto.getServeyId())
                .title(serveyResponseDto.getTitle())
                .type(serveyResponseDto.getType())
                .category(serveyResponseDto.getCategory())
                .perPoint(serveyResponseDto.getPerPoint())
                .limitSubmit(serveyResponseDto.getLimitSubmit())
                .startdate(serveyResponseDto.getStartdate())
                .enddate(serveyResponseDto.getEnddate())
                .build();
    }
}