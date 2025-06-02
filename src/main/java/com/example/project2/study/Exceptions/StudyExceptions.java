package com.example.project2.study.Exceptions;

import java.util.List;
import java.time.LocalDateTime;

public class StudyExceptions extends RuntimeException {

    public StudyExceptions(String message) {
        super(message);
    }
}







//
//public class StudyExceptions extends RuntimeException{
//
//    public StudyExceptions(String s)  {super(s);}
//
//    public StudyExceptions(String reason, List<String> erros) {
//         super(String.format("%s -%s", reason, erros));
//    }
//}
