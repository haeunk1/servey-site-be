package com.project.servey.adapter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;

import com.project.servey.adapter.in.web.dto.response.servey.ServeyListResponseDto;
import com.project.servey.adapter.out.persistence.entity.servey.ServeyEntity;
import com.project.servey.adapter.out.persistence.repository.ServeyRepository;
import com.project.servey.adapter.out.persistence.repository.dsl.ServeyDslRepository;
import com.project.servey.application.command.servey.FindServeyListCommand;
import com.project.servey.application.port.out.servey.CreateServeyPort;
import com.project.servey.application.port.out.servey.DeleteServeyPort;
import com.project.servey.application.port.out.servey.FindServeyPort;
import com.project.servey.application.port.out.servey.UpdateServeyPort;
import com.project.servey.domain.Servey;
import com.project.servey.exception.ErrorCode;
import com.project.servey.exception.ServeyException;
import com.project.servey.mapper.ServeyMapper;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class ServeyPersistenceAdapter implements CreateServeyPort, FindServeyPort, DeleteServeyPort, UpdateServeyPort{
    private final ServeyRepository serveyRepository;
    private final ServeyMapper serveyMapper;
    private final ServeyDslRepository dslRepository;
    
    @Override
    public Servey findServeyById(Long id) {
        ServeyEntity serveyEntity = serveyRepository.findById(id)
                    .orElseThrow(() -> new ServeyException(ErrorCode.SERVEY_NOT_FOUND));
        return serveyMapper.entityToDomain(serveyEntity);
    }

    
    @Override
    public List<Servey> findServeyAllList(){
        List<ServeyEntity> list = serveyRepository.findAll();
        return serveyMapper.entitiesToDomains(list);
    }

    /*
     * [Create] 설문조사 생성
    */
    @Override
    public Servey createServey(Servey servey) {
        ServeyEntity serveyEntity = serveyMapper.domainToEntity(servey);
        ServeyEntity savedEntity = serveyRepository.save(serveyEntity);

        return serveyMapper.entityToDomain(savedEntity);
    }

    /*
     * [Delete] 설문조사 삭제
    */
    @Override
    public Long deleteServey(Long serveyId) {
        return dslRepository.deleteServey(serveyId);
    }

    /*
     * [Update] 설문조사 수정
    */
    @Override
    public Long updateServey(Servey servey) {
        return dslRepository.updateServey(servey);
    }

    /*
     * [Read] 필터링 조회
    */
    @Override
    public List<ServeyListResponseDto> findServeyFilteredList(FindServeyListCommand command) {
         //command로 넘기기..
         return dslRepository.selectServeyFilteredList(command);
    }

    /*
     * [Read] serveyId, deleteYn 으로 조회
     * 조건에 해당하는 게시글 존재하면 true, 존재하지 않으면 false
    */
    @Override
    public boolean checkIsServeyExist(Long id) {
        Optional<ServeyEntity> serveyEntity = serveyRepository.findByServeyIdAndDeleteYn(id,"N");
        return serveyEntity.isPresent();
    }
    
}