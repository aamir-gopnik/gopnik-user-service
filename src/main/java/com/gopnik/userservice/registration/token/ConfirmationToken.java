package com.gopnik.userservice.registration.token;

import com.gopnik.userservice.appuser.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken implements Serializable {

    @Id
    @SequenceGenerator(
            name="confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime creationTime;

    @Column(nullable = false)
    private LocalDateTime expireTime;

    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public ConfirmationToken(String token, LocalDateTime creationTime, LocalDateTime expireTime, AppUser appUser) {
        this.token = token;
        this.creationTime = creationTime;
        this.expireTime = expireTime;
        this.appUser = appUser;
    }
}
