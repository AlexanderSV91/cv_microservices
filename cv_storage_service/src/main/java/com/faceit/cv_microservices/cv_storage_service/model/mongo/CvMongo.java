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
public class CvMongo {

    @Id
    private String id;
    private String titleCv;
    private SalaryMongo salary;
    private UserMongo user;
    private List<String> typeOfEmployments;
    private String education;
    private List<PreviousWorkMongo> previousWorks;
    private String hrefCv;
}
