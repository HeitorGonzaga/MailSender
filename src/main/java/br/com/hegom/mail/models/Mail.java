package br.com.hegom.mail.models;

import br.com.hegom.mail.repository.TypeStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Table(name = "mail")
@Entity(name = "mail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String sender;

    private String receiver;

    private String subject;

    @Column(columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private LocalDate createDate;

    private LocalDate sendingDate;

    private LocalTime sendingTime;

    @Enumerated(EnumType.STRING)
    private TypeStatus status;

    @PrePersist
    private void prePersist(){
        this.createDate = LocalDate.now();
    }

    @PreUpdate
    private void preUpdate(){
        sendingDate = LocalDate.now();
        sendingTime = LocalTime.now();
    }


}
