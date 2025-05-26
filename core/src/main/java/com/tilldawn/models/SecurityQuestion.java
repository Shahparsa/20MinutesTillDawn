package com.tilldawn.models;

import com.tilldawn.models.enums.SecurityQuestionType;

public class SecurityQuestion {
    SecurityQuestionType type;
    String answer;

    public SecurityQuestion(SecurityQuestionType type, String answer) {
        this.type = type;
        this.answer = answer;
    }

    public SecurityQuestionType getType() {
        return type;
    }

    public void setType(SecurityQuestionType type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
