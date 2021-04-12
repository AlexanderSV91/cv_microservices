package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "cv")
public class CvMongoModel {

    @Id
    private String id;
    private String titleCv;
    private SalaryMongoModel salaryMongoModel;
    private UserMongoModel userMongoModel;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkMongoModel> previousWorkMongoModels;
    private String hrefCv;
}
