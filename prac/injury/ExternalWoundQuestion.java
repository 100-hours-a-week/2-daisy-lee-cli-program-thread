package prac.injury;

public enum ExternalWoundQuestion {
    QUES_1("점점 더 심해지나요?", "NONE"),
    QUES_2("계속 일정하게 아픈가요?", "NONE"),
    QUES_3("주기적으로 아픈가요?", "NONE"),
    QUES_4("특정 시간이나 활동 시에만 아픈가요?", "NONE");

    private String quesText;
    private String answer;

    ExternalWoundQuestion(String quesText, String answer) {
        this.quesText = quesText;
        this.answer = answer;
    }

    public String getQuesText() {
        return quesText;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }
}
