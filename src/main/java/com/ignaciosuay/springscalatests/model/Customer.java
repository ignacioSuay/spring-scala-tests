package com.ignaciosuay.springscalatests.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {

    private Long id;
    private String name;

}
