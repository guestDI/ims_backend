package com.ims.qa;

import com.ims.qa.model.Interviewer;
import com.ims.qa.repository.InterviewerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(propagation = Propagation.REQUIRED)
public class DataLoader implements ApplicationRunner {

    @Autowired
    private InterviewerRepository interviewerRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception{
        Interviewer d_ihnatovich = new Interviewer("Dzmitry", "Ihnatovich", "d.ihnatovich@godeltech.com",
                "Minsk", 30);
        Interviewer v_dudarevich = new Interviewer("Vitaliy", "Dudarevich", "v.dudarevich@godeltech.com",
                "Minsk", 20);
        Interviewer h_kurak = new Interviewer("Hanna", "Kurak", "h.kurak@godeltech.com",
                "Brest", 10);
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
    }
}
