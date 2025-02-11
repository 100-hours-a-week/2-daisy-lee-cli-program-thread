package prac;

import prac.human.ExpertPersonality;
import prac.human.Intern;
import prac.human.InternPersonality;
import prac.human.ResidentPersonality;

import static prac.human.Healer.healer;

public class HealerPersonalityThread implements Runnable{
    @Override
    public void run() {
        String healerClass = healer.getClass().getSimpleName();
        int healerPersonalityRandom = 0;
        switch(healerClass) {
            case "Intern":
                healerPersonalityRandom = InternPersonality.getRandomPersonality().getBuffOrDebuff();
                break;
            case "Resident":
                healerPersonalityRandom = ResidentPersonality.getRandomPersonality().getBuffOrDebuff();
                break;
            case "Expert" :
                healerPersonalityRandom = ExpertPersonality.getRandomPersonality().getBuffOrDebuff();
                break;
            default:
                break;
        }
        healer.setResultProbability(healer.getResultProbability() + healerPersonalityRandom);
    }
}
