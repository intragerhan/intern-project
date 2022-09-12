package com.intern.ambassador.data.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;

    @Lob
    @Column(nullable = false)
    private String reason;

    @Lob
    @Column(nullable = false)
    private String feedback;

    @Lob
    @Column(nullable = false)
    private String activity;

    @Lob
    @Column(nullable = false)
    private String advantage;

    @Lob
    @Column(nullable = false)
    private String lastWord;

}
