package prac;
import prac.human.Expert;
import prac.human.Intern;
import prac.human.Oneself;
import prac.human.Resident;

import static prac.human.Healer.healer;
import static prac.human.Patient.patient;

public class HealerGenerateThread implements Runnable{

    @Override
    public void run() {
        int randomNum = (int) (Math.random() * (4) + 1);
        switch(randomNum) {
            case 1:
                healer = new Intern("1년 미만 실습생", 100000, 30, 50);
                break;
            case 2:
                healer = new Resident("3년 이상 레지던트", 200000, 50, 60);
                break;
            case 3:
                healer = new Expert("10년 이상 베테랑", 700000, 70, 80);
                break;
            case 4:
                healer = new Oneself("본인", 0, 0, 100);
                break;
        }
        patient.setHealerType(healer);
    }
}
