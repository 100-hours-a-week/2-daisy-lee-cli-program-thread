package prac.injury;

import static prac.Description.sc;
import static prac.human.Patient.patient;

public class ExternalWound extends Injury {
    int i = 0;
    public ExternalWound() {
        super();
    }

    // 외상 종류 출력 및 선택
    @Override
    public void woundList() {
        ExternalWounds[] externalWoundArr = ExternalWounds.values();
        int cost;
        String costStr;
        for(ExternalWounds externalWound : externalWoundArr) {
            cost = externalWound.getWoundCost();
            costStr = String.format("%,d", cost);
            System.out.printf(" %d. %s (+ ₩" + costStr + ")", externalWound.getWoundNumber(), externalWound.getWoundName());
            System.out.println();
        }
    }

    @Override
    public String findHospital() {
        String hospital = null;
        int woundType = patient.getWoundType();
        hospital = switch (woundType) {
            case 1 -> "외과";
            case 2 -> "내과";
            case 3 -> "정형외과";
            default -> hospital;
        };
        return hospital;
    }

    @Override
    public void questions() {
        ExternalWoundQuestion[] iwqArr = ExternalWoundQuestion.values();
        while(i < iwqArr.length){
            System.out.printf(" %d. %s (y/n)", iwqArr[i].ordinal()+1, iwqArr[i].getQuesText());
            System.out.println();
            System.out.print(" >> ");
            String answer = sc.next();
            answerSave(iwqArr[i], answer);
        }
    }

    void answerSave(ExternalWoundQuestion iwq, String answer) {
        if(answer.equalsIgnoreCase("YES") || answer.equalsIgnoreCase("Y")) {
            iwq.setAnswer("YES");
            i++;
        } else if(answer.equalsIgnoreCase("NO") || answer.equalsIgnoreCase("N")) {
            iwq.setAnswer("NO");
            i++;
        } else {
            System.out.println("    환자분, 올바른 대답을 하세요.");
        }
    }

    @Override
    public boolean isAllNo() {
        boolean flag = false;
        int answerCount = 0;
        ExternalWoundQuestion[] iwqArr = ExternalWoundQuestion.values();
        for(ExternalWoundQuestion iwq : iwqArr) {
            String answer = iwq.getAnswer();
            if(answer.equals("NO")){
                answerCount++;
            }
        }

        // 모든 질문에 NO를 한 경우
        if(answerCount == ExternalWoundQuestion.values().length) {
           flag = true;
        }
        return flag;
    }

}


