package com.faceit.cv_microservices.cv_storage_service.mapper;

import com.faceit.cv_microservices.cv_storage_service.dto.request.CvRequest;
import com.faceit.cv_microservices.cv_storage_service.dto.request.PreviousWorkRequest;
import com.faceit.cv_microservices.cv_storage_service.dto.request.SalaryRequest;
import com.faceit.cv_microservices.cv_storage_service.dto.request.UserRequest;
import com.faceit.cv_microservices.cv_storage_service.model.elastic.CvElasticModel;
import com.faceit.cv_microservices.cv_storage_service.model.elastic.PreviousWorkElasticModel;
import com.faceit.cv_microservices.cv_storage_service.model.elastic.SalaryElasticModel;
import com.faceit.cv_microservices.cv_storage_service.model.elastic.UserElasticModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CvElasticMapper {

    List<CvElasticModel> toCvElasticModelList(List<CvRequest> cvRequestList);

    @Mappings({
            @Mapping(target = "userElasticModel", source = "cvRequest.userRequest"),
            @Mapping(target = "salaryElasticModel", source = "cvRequest.salaryRequest"),
            @Mapping(target = "previousWorkElasticModels", source = "cvRequest.previousWorkRequests")})
    CvElasticModel toCvElasticModel(CvRequest cvRequest);

    List<PreviousWorkElasticModel> toPreviousWorkElasticModelList(List<PreviousWorkRequest> previousWorkRequestList);

    PreviousWorkElasticModel toPreviousWorkElasticModel(PreviousWorkRequest previousWorkRequest);

    SalaryElasticModel toSalaryElasticModel(SalaryRequest salaryRequest);

    UserElasticModel toUserElasticModel(UserRequest userRequest);
}
