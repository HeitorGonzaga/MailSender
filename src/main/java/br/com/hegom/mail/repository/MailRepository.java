package br.com.hegom.mail.repository;

import br.com.hegom.mail.models.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    List<Mail> findByCreateDate(LocalDate createDate);

    List<Mail> findByStatus(TypeStatus status);
}
