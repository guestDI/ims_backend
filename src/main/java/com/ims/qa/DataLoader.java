package com.ims.qa;

import com.ims.qa.enums.Level;
import com.ims.qa.enums.Status;
import com.ims.qa.model.Candidate;
import com.ims.qa.model.Interview;
import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.CandidateRepository;
import com.ims.qa.repository.InterviewRepository;
import com.ims.qa.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.ims.qa.enums.Status.*;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DataLoader implements ApplicationRunner {

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private InterviewRepository interviewRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception{
        Interviewer d_ihnatovich = new Interviewer("Dzmitry", "Ihnatovich", "d.ihnatovich@godeltech.com",
                "Minsk", Level.LEAD, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/d_ihnatovich.jpg");
        Interviewer v_dudarevich = new Interviewer("Vitaliy", "Dudarevich", "v.dudarevich@godeltech.com",
                "Minsk", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561555901/ims/interviewers/v_dudarevich.png");
        Interviewer h_kurak = new Interviewer("Hanna", "Kurak", "h.kurak@godeltech.com",
                "Brest", Level.LEAD, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/h_kurak.png");
        Interviewer v_khudzinskaya = new Interviewer("Volha", "Khudzinskaya", "v.khudzinskaya@godeltech.com",
                "Minsk", Level.LEAD, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/v_khudzinskaya.png");
        Interviewer y_moroz = new Interviewer("Yury", "Moroz", "y.moroz@godeltech.com",
                "Brest", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/y_maroz.png");
        Interviewer t_lisovskaya = new Interviewer("Tatsiana", "Lisovskaya", "t.lisovskaya@godeltech.com",
                "Minsk", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/t_lisouskaya.png");
        Interviewer e_solonina = new Interviewer("Elena", "Solonina", "e.solonina@godeltech.com",
                "Minsk", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/e_solonina.png");
        Interviewer s_kamisarava = new Interviewer("Sviatlana", "Kamisarava", "s.kamisarava@godeltech.com",
                "Grodno", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561555967/ims/interviewers/s_kamisarava.png");
        Interviewer v_mukasei = new Interviewer("Volha", "Mukasei", "v.mukasei@godeltech.com",
                "Minsk", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473149/ims/interviewers/v_mukasei.png");
        Interviewer n_kurilenko = new Interviewer("Natalia", "Kurilenko", "n.kurilenko@godeltech.com",
                "Gomel", Level.LEAD, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/n_kurilenko.png");
        Interviewer s_milaserdov = new Interviewer("Semen", "Milaserdov", "s.milaserdov@godeltech.com",
                "Brest", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/s_milaserdov.png");
        Interviewer a_putrych = new Interviewer("Aliaksei", "Putrych", "a.putrych@godeltech.com",
                "Grodno", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/v_putrych.png");
        Interviewer v_rodin = new Interviewer("Vitaliy", "Rodin", "v.rodin@godeltech.com",
                "Minsk", Level.SENIOR, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473149/ims/interviewers/v_rodin.png");
        Interviewer o_balko = new Interviewer("Olga", "Balko", "o.balko@godeltech.com",
                "Minsk", Level.LEAD, "https://res.cloudinary.com/dxp2voatn/image/upload/v1561473148/ims/interviewers/o_balko.png");

        interviewerRepository.save(d_ihnatovich);
        interviewerRepository.save(v_dudarevich);
        interviewerRepository.save(h_kurak);
        interviewerRepository.save(v_khudzinskaya);
        interviewerRepository.save(y_moroz);
        interviewerRepository.save(t_lisovskaya);
        interviewerRepository.save(e_solonina);
        interviewerRepository.save(s_kamisarava);
        interviewerRepository.save(v_mukasei);
        interviewerRepository.save(n_kurilenko);
        interviewerRepository.save(s_milaserdov);
        interviewerRepository.save(a_putrych);
        interviewerRepository.save(v_rodin);
        interviewerRepository.save(o_balko);

        Candidate ag = Candidate.builder()
                .firstname("Alexander")
                .lastname("Gavrilov")
                .level(Level.MIDDLE)
                .location("Mogilev")
                .build();

        Candidate ay = Candidate.builder()
                .firstname("Anton")
                .lastname("Yurkevich")
                .level(Level.MIDDLE)
                .location("Minsk")
                .build();

        Candidate ar = Candidate.builder()
                .firstname("Alexandra")
                .lastname("Ragulina")
                .level(Level.SENIOR)
                .location("Minsk")
                .build();

        Candidate ik = Candidate.builder()
                .firstname("Ivan")
                .lastname("Katsenya")
                .level(Level.MIDDLE)
                .location("Brest")
                .build();

        candidateRepository.save(ag);
        candidateRepository.save(ay);
        candidateRepository.save(ar);
        candidateRepository.save(ik);

        Interview first = new Interview(ag, new ArrayList<>(Arrays.asList(v_khudzinskaya, v_dudarevich)), DONE);
        Interview second = new Interview(ay, new ArrayList<>(Collections.singletonList(v_mukasei)), DONE);
        Interview third = new Interview(ar, new ArrayList<>(Collections.singletonList(d_ihnatovich)), REJECTED);
        Interview fourth = new Interview(ik, new ArrayList<>(Arrays.asList(h_kurak, y_moroz)), JO_REJECTED);

        interviewRepository.save(first);
        interviewRepository.save(second);
        interviewRepository.save(third);
        interviewRepository.save(fourth);
    }
}
