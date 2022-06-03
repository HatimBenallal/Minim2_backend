package edu.upc.dsa.models;

public class FAQ {
    String quest;
    String answer;

    public FAQ() {
    }

    public FAQ(String quest, String answer) {
        this();
        this.setAnswer(answer);
        this.setQuest(quest);
    }

    public String getQuest() {
        return this.quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}

