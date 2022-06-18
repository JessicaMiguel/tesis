package com.example.tesis.process;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ResultProcess {

    private List<String> errors;
    private HttpStatus status;
    private Long idProcess;
    private String message;
}