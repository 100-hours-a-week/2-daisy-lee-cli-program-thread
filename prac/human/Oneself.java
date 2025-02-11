package prac.human;

import static prac.human.Patient.patient;

public class Oneself extends Healer{
    public Oneself(String type, int cost, int minProbability, int maxProbability) {
        super(type, cost, minProbability, maxProbability);
    }

    @Override
    public void addMessage(Healer healer) {
        System.out.println("아이고 행운을 빌어요~");
    }
}
