package br.com.hegom.mail.controllers;
import br.com.hegom.mail.models.Mail;
import br.com.hegom.mail.payload.MailDto;
import br.com.hegom.mail.repository.TypeStatus;
import br.com.hegom.mail.services.MailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("mail")
public class MailController {

    @Autowired
    private MailService service;

    @PostMapping
    public void save(@RequestBody MailDto data){
        Mail mail = new Mail();
        BeanUtils.copyProperties(data, mail);
        mail.setStatus(TypeStatus.WAITING);
        this.service.save(mail);
    }

    @GetMapping
    public List<Mail> findBySendingDate(@RequestParam String date){
        try{
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate data = LocalDate.parse(date, df);
            return this.service.findBySendingDate(data);
        }catch(Exception e){
            return null;
        }

    }

}
