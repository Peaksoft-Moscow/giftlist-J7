package com.peakosoft.giftlistj7.model.dto.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<RQST, ENTITY, RSPSN> {
    ENTITY mapToEntity(RQST rqst);

    RSPSN mapToResponse(ENTITY entity);
}
