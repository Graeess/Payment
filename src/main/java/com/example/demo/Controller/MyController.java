package com.example.demo.Controller;

import com.example.demo.Model.MyClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/class")
    public MyClass getMyClass() {
        MyClass myClass = new MyClass();
        myClass.setName("John");
        myClass.setAge(30);
        return myClass;
    }
}

