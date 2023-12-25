package com.peakosoft.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<RQST, ENTITY, RSPSN> {
    ENTITY mapToEntity(RQST rqst);

    RSPSN mapToResponse(ENTITY entity);
}
