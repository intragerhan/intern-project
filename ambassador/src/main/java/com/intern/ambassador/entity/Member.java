package com.intern.ambassador.entity;

import com.intern.ambassador.constant.Gender;
import com.intern.ambassador.constant.Role;
import com.intern.ambassador.domain.MemberDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter @Setter
@ToString
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;
    private String name;

    @Column(unique = true)
    private String email;

    private String address;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();

        member.setName(memberDto.getName());
        member.setEmail(memberDto.getEmail());
        member.setAddress(memberDto.getAddress());

        String password = passwordEncoder.encode(memberDto.getPassword());
        member.setPassword(password);

        member.setGender(memberDto.getGender());

        member.setRole(Role.USER);
        return member;
    }
}
