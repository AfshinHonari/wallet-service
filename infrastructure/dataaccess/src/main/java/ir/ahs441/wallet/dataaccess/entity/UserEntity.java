package ir.ahs441.wallet.dataaccess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
@Entity
public class UserEntity {

    @Id
    private Long id;

    private BigDecimal balance;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "user")
    @Column(nullable = false)
    private Set<TransactionEntity> transactions = new LinkedHashSet<>();

    @Version
    private Long version;
}
