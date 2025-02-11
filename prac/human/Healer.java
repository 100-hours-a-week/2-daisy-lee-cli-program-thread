package prac.human;

import static prac.human.Patient.patient;

public class Healer extends Human {

    public static Healer healer = new Healer();
    String type;
    int cost;
    int minProbability;
    int maxProbability;
    int resultProbability;

    public Healer() {
        super();
    }

    Healer(String type, int cost, int minProbability, int maxProbability) {
        super();
        this.type = type;
        this.cost = cost;
        this.minProbability = minProbability;
        this.maxProbability = maxProbability;
    }

    public void addMessage(Healer healer) {
        System.out.printf("%s 님의 수술 진행자는 [%s] 으로 배정되었습니다!", patient.getName(), patient.getHealerType().getType());
    }

    public String getType() {
        return type;
    }

    public int getCost() {
        return cost;
    }

    public int getMinProbability() {
        return minProbability;
    }

    public int getMaxProbability() {
        return maxProbability;
    }

    public int getResultProbability() {
        return resultProbability;
    }

    public void setResultProbability(int resultProbability) {
        this.resultProbability = resultProbability;
    }
}
