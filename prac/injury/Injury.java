package prac.injury;

import java.util.InputMismatchException;
import java.util.Scanner;

import static prac.Description.sc;
import static prac.human.Patient.patient;

public class Injury {
    public static Injury injury = new Injury();
    String hospitalName;
    int answer;

    public Injury() {

    }

    // 부상 종류 출력 및 선택
    public void injuryList() {
        Injuries[] injuryArr = Injuries.values();
        for(Injuries injury : injuryArr) {
            int cost = injury.getInjuryCost();
            String costStr = String.format("%,d",cost);
            System.out.printf(" %d. %s (+ ₩ %s)", injury.getInjuryNumber(), injury.getInjuryName(), costStr);
            System.out.println();
        }
    }

    // 부상 정보 set
    public void setInjury() {
        while(true) {
            try {
                System.out.print(" >> ");
                answer = sc.nextInt();
                if(answer == 1) {   // 외상으로 다시 선언
                    injury = new ExternalWound();
                } else {            // 내상으로 다시 선언
                    injury = new InternalWound();
                }
                patient.setInjuryType(Injuries.of(answer));
                System.out.println();
                break;
            } catch (InputMismatchException | NullPointerException e) {
                System.out.println("  ※ 올바른 값을 입력하세요. ※");
                sc = new Scanner(System.in);
            }
        }
    }

    // 선택된 부상 종류 출력 (시나리오용)
    public void checkInjury() {
        System.out.println("네, [" + patient.getInjuryType().getInjuryName() + "] 으로 접수해드리겠습니다.");
    }

    // 외상/내상에 따른 치료부위 목록 출력
    public void woundList() {
        System.out.println("부위를 선택합니다.");
    }

    // 치료부위에 따른 병원 이송
    public String findHospital() {
        System.out.println("환자를 이송합니다.");
        return null;
    }

    public void questions(){

    }

    public boolean isAllNo() {
        // ExternalWound 일 경우 체크
        return false;
    }

    public String getHospitalName() {
        return hospitalName;
    }
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
