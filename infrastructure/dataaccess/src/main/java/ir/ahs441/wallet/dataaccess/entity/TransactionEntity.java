package ir.ahs441.wallet.dataaccess.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TRANSACTIONS", indexes = {
        @Index(name = "CREATED_AT_IDX", columnList = "CREATED_AT")
})
@Entity
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne(targetEntity = UserEntity.class, cascade = CascadeType.MERGE)
    @Fetch(FetchMode.JOIN)
    private UserEntity user;

    @Column(nullable = false, name = "CREATED_AT")
    private LocalDateTime createdAt;

    public enum TransactionType {
        DEPOSIT,
        WITHDRAW
    }
}
