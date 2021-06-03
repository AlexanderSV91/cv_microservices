package com.faceit.cv_microservices.cv_storage_service.model.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
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
