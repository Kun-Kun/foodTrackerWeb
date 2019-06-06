package servlets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TestEntity {

    private Integer id;
    private String name;
    private String specialty;
    private Integer salary;

}
