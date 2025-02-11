import prac.Description;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        Description description = new Description();
        description.start();            // 프로그램 시작

        description.setPatientInfo();   // 환자 정보 입력(injury, name, wound)
        description.setHealer();        // 치료자 선택
        description.handlePay();        // 결제 진행
        description.processOperation(); // 수술 시작/종료

        description.end();              // 프로그램 종료
    }
}