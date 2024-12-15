package com.example.demo.Model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class MyClass {
    private String name;
    private int age;
}
