package ir.ahs441.wallet.dataaccess.repository;

import ir.ahs441.wallet.dataaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
}
