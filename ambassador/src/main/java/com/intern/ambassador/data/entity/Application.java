package com.intern.ambassador.data.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "application")
public class Application extends BaseEntity {

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

//    @OneToOne(name = "user_id")
}
