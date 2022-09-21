package com.intern.ambassador.data.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table
public class Apply extends BaseEntity {

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
