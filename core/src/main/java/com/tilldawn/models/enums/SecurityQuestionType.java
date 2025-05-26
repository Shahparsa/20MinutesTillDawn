package com.tilldawn.models.enums;

public enum SecurityQuestionType {
    Q1("Pas misham?"),
    Q2("What is your dad's name?"),
    Q3("What is your dad's age?"),;

    private String question;

    SecurityQuestionType(String s) {
        question = s;
    }

    public String getQuestion() {
        return question;
    }

    public static SecurityQuestionType getSecurityfromQuestion(String Question) {
        if(Question.equals("Pas misham?")){
            return Q1;
        }else if(Question.equals("What is your dad's name?")){
            return Q2;
        }else{
            return Q3;
        }
    }
}
