package com.faceit.cv_microservices.cv_service.mapper;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.model.mongo.CvMongo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CvMongoMapper {

    List<CvResponse> toCvResponseList(List<CvMongo> cvRequestList);
}
