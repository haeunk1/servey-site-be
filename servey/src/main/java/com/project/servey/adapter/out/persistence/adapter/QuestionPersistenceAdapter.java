package com.project.servey.adapter.out.persistence.adapter;

import java.util.List;

import com.project.servey.adapter.out.persistence.entity.servey.QuestionEntity;
import com.project.servey.adapter.out.persistence.repository.QuestionRepository;
import com.project.servey.application.port.out.question.CreateQuestionPort;
import com.project.servey.application.port.out.question.FindQuestionPort;
import com.project.servey.domain.Question;
import com.project.servey.mapper.QuestionMapper;
import com.project.servey.util.custom.PersistenceAdapter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@PersistenceAdapter
public class QuestionPersistenceAdapter implements CreateQuestionPort, FindQuestionPort{
    private final QuestionRepository repository;
    private final QuestionMapper mapper;

    @Override
    public List<Question> createQuestion(List<Question> list) {
        //domain to entity
        List<QuestionEntity> entityList = mapper.domainsToEntities(list);
        List<QuestionEntity> rtnEntityList =repository.saveAll(entityList);
        return mapper.entitiesToDomains(rtnEntityList);
    }

    @Override
    public List<Question> findQuestionList(Long serveyId) {
        List<QuestionEntity> rtnEntityList = repository.findByServeyId(serveyId);
        return mapper.entitiesToDomains(rtnEntityList);
    }
    
}
