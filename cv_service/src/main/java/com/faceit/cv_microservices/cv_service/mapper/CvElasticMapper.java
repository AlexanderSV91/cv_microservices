package com.faceit.cv_microservices.cv_service.mapper;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.model.elastic.CvElastic;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CvElasticMapper {

    List<CvResponse> toCvResponseList(List<CvElastic> cvElasticList);

    CvResponse toCvResponse(CvElastic cv);
}
