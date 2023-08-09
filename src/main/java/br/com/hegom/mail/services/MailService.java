package br.com.hegom.mail.services;

import br.com.hegom.mail.models.Mail;
import br.com.hegom.mail.repository.MailRepository;
import br.com.hegom.mail.repository.TypeStatus;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class MailService {

    @Autowired
    private MailRepository repository;

    @Autowired
    private JavaMailSender emailSender;


    public void save(Mail data){
        this.repository.save(data);
    }

    public List<Mail> findBySendingDate(LocalDate date){

        return this.repository.findByCreateDate(date);
    }

    @Scheduled(fixedRate = 15000)
    public void sendEmail(){
        Mail inFileMail = this.repository.findByStatus(TypeStatus.WAITING);
        if(inFileMail!=null){
            try{
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
                helper.setFrom(inFileMail.getSender());
                helper.setTo(inFileMail.getReceiver());
                helper.setSubject(inFileMail.getSubject());
                helper.setText(inFileMail.getText(), true);
                this.emailSender.send(message);
                inFileMail.setStatus(TypeStatus.SENT);
            }catch (Exception e){
                inFileMail.setStatus(TypeStatus.FAILED);
            }finally {
                this.repository.save(inFileMail);
            }
        }
    }

}
