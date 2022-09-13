package com.intern.ambassador.data.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "application")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ano;

    @Lob
    private String reason;

    @Lob
    private String feedback;

    @Lob
    private String activity;

    @Lob
    private String advantage;

    @Lob
    private String lastWord;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
