package dev5.chermenin.dao.specifications;


import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
