package prac;

import prac.human.*;
import prac.injury.ExternalWounds;
import prac.injury.Injuries;
import prac.injury.InternalWounds;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static prac.human.Healer.healer;
import static prac.human.Patient.patient;
import static prac.injury.Injuries.EXTERNAL;
import static prac.injury.Injury.injury;

public class Description {
    public static Scanner sc = new Scanner(System.in);
    Thread thread = null;

    public Description() {
    }

    /** Program Start/End **/
    // 프로그램 시작
    public void start() {
        System.out.println("━━━━━━━━━━━━━━━ ➕ ━━━━━━━━━━━━━━━━━━\n");
        System.out.println("        ꕥ 운수좋은날 종합병원 ꕥ");
        System.out.println("  \"당신이 가장 운 좋은 환자가 되길 바라며!\"\n");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        nurseSmile();
        System.out.println(" 안녕하세요~ 어디가 아파서 오셨나요?");
    }

    // 프로그램 종료
    public void end() {
        line();
        System.out.println("          - T H E     E N D -");
    }

    /** Pateint Info (Name, InjuryType, WoundType, HealerType) **/
    // 환자의 기본 정보를 입력
    public void setPatientInfo() {
        setPatientInjury();         // 부상 분류 설정
        setPatientName();           // 환자 이름 설정
        setPatientWound();          // 치료 부위 설정
//        if(patient.getInjuryType() == EXTERNAL) {   // TODO. 외상일 경우, 추가 시나리오 .. 미구현
//            askPatient();
//        }
    }

    // 부상 분류 설정
    public void setPatientInjury() {
        injury.injuryList();
        injury.setInjury();
        line();
        nurseSmile();
        injury.checkInjury();
    }

    // 환자 이름 설정
    public void setPatientName() {
        System.out.println("     성함은 어떻게 되실까요? ");
        select();
        String name = sc.next();
        patient.setName(name);
        line();
    }

    // 치료 부위 설정
    public void setPatientWound() {
        int answer;
        nurseSmile();
        System.out.println("\'" + patient.getName() + "\' 님, 해당 부위에 대한 추가적인 정보가 필요합니다.");
        System.out.println("      어떤 [" + patient.getInjuryType().getInjuryName() + "]에 해당하는지 선택해주세요.");

        injury.woundList(); // 환자가 택한 부상 종류에 따른 치료 부위 출력
        while(true) {
            try {
                select();
                answer = sc.nextInt();
                patient.setWoundType(answer);   // 치료 부위 설정
                ExternalWounds.of(patient.getWoundType());
                moveToHospital();
                break;
            } catch (InputMismatchException | NullPointerException e) {
                System.out.println("  ※ 올바른 값을 입력하세요. ※");
                sc = new Scanner(System.in);
            }
        }
    }

    // 치료 부위에 해당하는 병원 설정 및 이동 애니메이션
    public void moveToHospital(){
        String[] dots = {" . ", ". ", " . "};
        String hospital = injury.findHospital();    // Method Overriding
        injury.setHospitalName(hospital);

        line();
        System.out.print(" " + injury.getHospitalName() + " (으)로 이동 중입니다");
        loadingArrays(dots);
        System.out.print(" 🏥");
        System.out.println();
    }

    // 수술 진행자 설정
    public void setHealer() {
        String[] dots = {" . ", " . ", " . "};
        line();
        doctorSmile();
        try {
            thread = null;
            // 2주차) 수술 진행자 배정 스레드
            HealerGenerateThread healerGenerate = new HealerGenerateThread();
            thread = new Thread(healerGenerate);
            thread.start();
            System.out.println("어서오세요 환자분, 이제 수술 진행할건데");
            System.out.println("     지금 저희 병원이 바빠서 선생님 배정되는 대로 안내드릴게요~");
            System.out.println("     연차별로 두 명씩 계신데, 운이 안 좋으면 본인이 직접 하셔야 할 수도 있으니 참고하시구요~");
            System.out.println();
            line();
            Thread.sleep(3000);
            System.out.print("  담당자 배정 중");
            loadingArrays(dots);
            System.out.println();

            doctorSmile();
            healer.addMessage(healer);
            System.out.println();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    /** Operation Process **/
    // 수술 프로세스 진행
    public void processOperation() {
        String[] onAirArr = {"O", "N", " ", "A", "I", "R", " ", "🔴"};
        // 2주차)
        HealerPersonalityThread healerPersonality = new HealerPersonalityThread();
        thread = new Thread(healerPersonality);
        thread.start();
        line();
        doctorSmile();
        System.out.println("지금 바로 수술실로 안내드리겠습니다!");
        System.out.println("     당신의 수술에 운이 깃들길 바라며..");
        System.out.println();

        System.out.println("= = = = = = = = =");
        loadingArrays(onAirArr);
        System.out.println();
        System.out.println("= = = = = = = = =");
        // 2주차) 수술 확률 설정 스레드
        HealerOperationThread healerOperation = new HealerOperationThread();
        thread = new Thread(healerOperation);
        thread.start();
        int randomNum = (int) (Math.random() * (100));

        System.out.println();
        line();
        System.out.println();
        if(randomNum <= healer.getResultProbability()) {
            successOperation();
        } else {
            failOperation();
        }
    }

    // 수술 진행자별 성공 확률 조정
    public static synchronized void setOperationProbability() {
        Healer healerInfo = patient.getHealerType();
        int minProbability = healerInfo.getMinProbability();
        int maxProbability = healerInfo.getMaxProbability();

        int resultProbability = (int) (Math.random() * (maxProbability - minProbability + 1) + minProbability );
        resultProbability += healer.getResultProbability();

        healer.setResultProbability(resultProbability);
    }

    // 수술 성공 시 출력 내용
    private void successOperation() {
        System.out.println("  수술이 성공적으로 마무리 되었습니다!");
        System.out.println("  수술이 잘 되어 지금 퇴원하셔도 무방합니다.");
        System.out.println();
        System.out.println("  안녕히 가십시오 ...");
    }

    // 수술 실패 시 출력 내용
    private void failOperation() {
        System.out.println("  수술이 실패하였습니다.");
        System.out.println("  병원에서는 이번 사건을 은폐하였고,");
        System.out.println("  당신은 \'영원한 마취\'에서 깨어나지 못했습니다 ...");
        System.out.println();
    }


    /** Pay Process **/
    // 결제 프로세스 진행
    public void handlePay() {
        int finalPayment = 0;
        line();
        doctorSmile();
        System.out.println("결제하시면 수술실로 안내하겠습니다 ^^.");
        System.out.println("     결제 내역은 아래와 같습니다.");
        printReceipt();
        doctorSmile();
        System.out.printf("총 지불하셔야 하는 금액 ₩ %s 되십니다~ ", String.format("%,d", patient.getPayment()));

        while(finalPayment < patient.getPayment()) {
            System.out.println();
            System.out.print(" >> 지불할 금액 : ");
            finalPayment = sc.nextInt();
            if(finalPayment < patient.getPayment()) {
                doctorFrown();
                System.out.println(".. 설마 돈이 부족하신 건 아니죠?");
            } else {
                doctorSmile();
                System.out.printf("거스름돈은 ₩ %s 입니다~\n", String.format("%,d", finalPayment - patient.getPayment()));
            }
        }
        System.out.println();
    }

    // 사용자 입력 정보 및 영수증 출력
    private void printReceipt() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date now = new Date();
        Injuries injuryInfo = patient.getInjuryType();
        int woundCost;
        String woundName;
        if(injuryInfo == EXTERNAL) {
            woundCost = ExternalWounds.of(patient.getWoundType()).getWoundCost();
            woundName = ExternalWounds.of(patient.getWoundType()).getWoundName();
        } else {
            woundCost = InternalWounds.of(patient.getWoundType()).getWoundCost();
            woundName = InternalWounds.of(patient.getWoundType()).getWoundName();
        }

        patient.pay(patient.getInjuryType().getInjuryCost()); // 부상
        patient.pay(woundCost); // 치료 부위
        patient.pay(patient.getHealerType().getCost()); // 수술 진행 비용

        // 영수증
        System.out.println(" ┌──────────────────────────────────────┐");
        System.out.println("           《  영    수    증  》");
        System.out.println();
        System.out.printf("   성함 : %s\n", patient.getName());
        System.out.printf("   부상 종류 - [%s]\n", patient.getInjuryType().getInjuryName());
        System.out.printf("    ⇒ [%s] 으로 인한 통증 호소\n", woundName);
        System.out.printf("   (₩ %s + ₩ %s)\n", String.format("%,d", patient.getInjuryType().getInjuryCost()), String.format("%,d", woundCost));
        System.out.println();
        System.out.printf("   수술 진행 병원 : [%s] \n", injury.getHospitalName());
        System.out.printf("   수술 진행자 : [%s] (₩ %s)\n", patient.getHealerType().getType(), String.format("%,d", patient.getHealerType().getCost()));
        System.out.println();
        System.out.printf("   { 합계 : ₩ %s }\n", String.format("%,d", patient.getPayment()));
        System.out.println("  - - - - - - - - - - - - - - - - - - - -");
        System.out.printf("                  %s", sdf.format(now)); // 실행 시킨 날짜
        System.out.println("\n");
        System.out.println("          ꕥ 운수좋은날 종합병원 ꕥ");
        System.out.println("     \"당신이 가장 운 좋은 환자가 되길 바라며!\"");
        System.out.println(" └──────────────────────────────────────┘");
    }


    /** 기타 출력 함수 **/
    // 콘솔 로딩 애니메이션용 함수
    private void loadingArrays(String[] loadingArr) {
        try {
            for(int i = 0; i < loadingArr.length; i++) {
                Thread.sleep(1000);
                System.out.print(loadingArr[i] + " ");
                System.out.flush();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void line() {
        System.out.println("─────────────────────────────────────");
    }
    private void select() { System.out.print(" >> "); }
    private void nurseSmile() {
        System.out.print("😄 : ");
    }
    private void doctorSmile() {
        System.out.print("🤓 : ");
    }
    private void doctorFrown() {
        System.out.print("🤨 : ");
    }

    /** 미구현 내용 **/
    /*public void askPatient() {
            doctorSmile();
            System.out.println("안녕하세요. \'" + patient.getName() + "\' 님, 치료에 앞서 진단을 할 예정입니다.");
            System.out.println("     제가 하는 질문에 Y 또는 N으로 \'정직하게\' 대답해주세요.");
            injury.questions();
            if(injury.isAllNo()) {
                expelPatient();
            }
     }*/

    /*private void expelPatient() {
        String expelTexts[] = {
                ". ", ". ", "?",
                "     환자분, 병원은 장난이 아닙니다.",
                "     그렇게 아프시지 않은가 본데, 다른 환자분들 진료가 더 급한 것 같네요.",
                "     엄연한 업무 방해죄 입니다.",
                "     저희 병원에서 나가주세요. 😡"
        };
        doctorFrown();
        try {
            for(int i = 0; i < 3; i++) {    // ..? 출력하기 위한.. for문
                System.out.print(expelTexts[i]);
                Thread.sleep(400);
                System.out.flush();
            }
            System.out.println();
            for(int i = 3; i < expelTexts.length; i++) {    // 문장을 출력하기 위한 for문
                System.out.println(expelTexts[i]);
                Thread.sleep(1000);
                System.out.flush();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }*/
}
