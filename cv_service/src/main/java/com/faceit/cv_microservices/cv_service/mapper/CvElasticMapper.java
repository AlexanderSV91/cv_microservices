package com.faceit.cv_microservices.cv_service.mapper;

import com.faceit.cv_microservices.cv_service.dto.response.CvResponse;
import com.faceit.cv_microservices.cv_service.dto.response.PreviousWorkResponse;
import com.faceit.cv_microservices.cv_service.dto.response.SalaryResponse;
import com.faceit.cv_microservices.cv_service.dto.response.UserResponse;
import com.faceit.cv_microservices.cv_service.model.elastic.CvElasticModel;
import com.faceit.cv_microservices.cv_service.model.elastic.PreviousWorkElasticModel;
import com.faceit.cv_microservices.cv_service.model.elastic.SalaryElasticModel;
import com.faceit.cv_microservices.cv_service.model.elastic.UserElasticModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CvElasticMapper {

    List<CvResponse> toCvResponseList(List<CvElasticModel> cvElasticModelList);

    @Mappings({
            @Mapping(target = "userResponse", source = "userElasticModel"),
            @Mapping(target = "salaryResponse", source = "salaryElasticModel"),
            @Mapping(target = "previousWorkResponses", source = "previousWorkElasticModels")})
    CvResponse toCvResponse(CvElasticModel cvElasticModel);

    List<PreviousWorkResponse> toPreviousWorkResponse(List<PreviousWorkElasticModel> previousWorkElasticModelList);

    PreviousWorkResponse toPreviousWorkResponse(PreviousWorkElasticModel previousWorkElasticModel);

    SalaryResponse toSalaryResponse(SalaryElasticModel salaryElasticModel);

    UserResponse toUserResponse(UserElasticModel userElasticModel);
}
