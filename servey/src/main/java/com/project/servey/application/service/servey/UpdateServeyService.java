package com.project.servey.application.service.servey;

import org.springframework.transaction.annotation.Transactional;

import com.project.servey.application.command.servey.UpdateServeyCommand;
import com.project.servey.application.port.in.servey.UpdateServeyUseCase;
import com.project.servey.application.port.out.servey.FindServeyPort;
import com.project.servey.application.port.out.servey.UpdateServeyPort;
import com.project.servey.domain.Servey;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.mapper.ServeyMapper;
import com.project.servey.util.custom.UseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = false)
@UseCase
public class UpdateServeyService implements UpdateServeyUseCase{
    
    private final UpdateServeyPort updateServeyPort;
    private final FindServeyService findService;
    private final ServeyMapper serveyMapper;

    @Override
    public Long updateServey(UpdateServeyCommand command) {
        Servey servey = serveyMapper.commandToDomain(command);

        //삭제된 게시글이 아닌지 검증
        findService.checkIsServeyExist(servey.getServeyId());
        return updateServeyPort.updateServey(servey);
    }
}
