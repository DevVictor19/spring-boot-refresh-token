package com.devvictor.spring_boot_refresh_token.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_refresh_tokens")
public class UserRefreshToken {
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "refresh_token", nullable = false, length = 255)
    private String refreshToken;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
