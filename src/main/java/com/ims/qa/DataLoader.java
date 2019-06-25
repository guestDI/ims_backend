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
import java.util.List;

import static com.ims.qa.enums.Status.DONE;
import static com.ims.qa.enums.Status.REJECTED;

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
                "Minsk", 73);
        Interviewer v_dudarevich = new Interviewer("Vitaliy", "Dudarevich", "v.dudarevich@godeltech.com",
                "Minsk", 99);
        Interviewer h_kurak = new Interviewer("Hanna", "Kurak", "h.kurak@godeltech.com",
                "Brest", 47);
        Interviewer v_khudzinskaya = new Interviewer("Volha", "Khudzinskaya", "v.khudzinskaya@godeltech.com",
                "Minsk", 40);
        Interviewer y_moroz = new Interviewer("Yury", "Moroz", "y.moroz@godeltech.com",
                "Minsk", 13);
        Interviewer t_lisovskaya = new Interviewer("Tatsiana", "Lisovskaya", "t.lisovskaya@godeltech.com",
                "Minsk", 9);
        Interviewer e_solonina = new Interviewer("Elena", "Solonina", "t.solonina@godeltech.com",
                "Minsk", 3);

        interviewerRepository.save(d_ihnatovich);
        interviewerRepository.save(v_dudarevich);
        interviewerRepository.save(h_kurak);
        interviewerRepository.save(v_khudzinskaya);
        interviewerRepository.save(y_moroz);
        interviewerRepository.save(t_lisovskaya);
        interviewerRepository.save(e_solonina);

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

        candidateRepository.save(ag);
        candidateRepository.save(ay);
        candidateRepository.save(ar);

        Interview first = new Interview(ag, new ArrayList<>(Arrays.asList(v_khudzinskaya, v_dudarevich)), DONE);
        Interview second = new Interview(ay, new ArrayList<>(Arrays.asList(v_khudzinskaya)), DONE);
        Interview third = new Interview(ar, new ArrayList<>(Arrays.asList(d_ihnatovich)), REJECTED);

        interviewRepository.save(first);
        interviewRepository.save(second);
        interviewRepository.save(third);
    }
}
