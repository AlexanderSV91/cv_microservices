package com.faceit.cv_microservices.cv_storage_service.mapper;

import com.faceit.cv_microservices.cv_storage_service.dto.request.CvRequest;
import com.faceit.cv_microservices.cv_storage_service.dto.request.PreviousWorkRequest;
import com.faceit.cv_microservices.cv_storage_service.dto.request.SalaryRequest;
import com.faceit.cv_microservices.cv_storage_service.dto.request.UserRequest;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.CvMongoModel;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.PreviousWorkMongoModel;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.SalaryMongoModel;
import com.faceit.cv_microservices.cv_storage_service.model.mongo.UserMongoModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CvMongoMapper {

    List<CvMongoModel> toCvMongoModelList(List<CvRequest> cvRequestList);

    @Mappings({
            @Mapping(target = "userMongoModel", source = "cvRequest.userRequest"),
            @Mapping(target = "salaryMongoModel", source = "cvRequest.salaryRequest"),
            @Mapping(target = "previousWorkMongoModels", source = "cvRequest.previousWorkRequests")})
    CvMongoModel toCvMongoModel(CvRequest cvRequest);

    List<PreviousWorkMongoModel> toPreviousWorkMongoModelList(List<PreviousWorkRequest> previousWorkRequestList);

    PreviousWorkMongoModel toPreviousWorkMongoModel(PreviousWorkRequest previousWorkRequest);

    SalaryMongoModel toSalaryMongoModel(SalaryRequest salaryRequest);

    UserMongoModel toUserMongoModel(UserRequest userRequest);
}
