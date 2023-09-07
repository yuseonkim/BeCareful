package com.example.becarefulbackendapi.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false) private String username;
    @Column(nullable = false) private String password;
    @Column(nullable = false) private String nickname;
    @Column(nullable = false) private String role;
//    @Column(nullable = false) @ColumnDefault("false") private Boolean isAgreed;
    @CreationTimestamp private Timestamp cratedDate;

    private String provider;
    private Long providerId;

    public User(String username, String password, String role,String nickname, String provider, Long providerId) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.provider = provider;
        this.providerId = providerId;
    }

    @Builder
    public User(String username, String password, String role, String nickname, String provider) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.nickname = nickname;
        this.provider = provider;
    }

//    public void updateAgree(Boolean isAgreed){
//        this.isAgreed = isAgreed;
//    }
}
