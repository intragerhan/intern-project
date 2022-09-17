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
@Table(name = "education")
public class Education extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eno;

    private String name;

    @Enumerated(EnumType.STRING)
    private Type type;

    public static enum Type {
        KNOWLEDGE, CONVERSATION, WRITING, QUALITY, TARGETING, SNS, CHANNEL
    }
}
