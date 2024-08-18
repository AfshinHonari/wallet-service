package ir.ahs441.wallet.domain.repository;

import ir.ahs441.wallet.domain.entity.User;
import ir.ahs441.wallet.domain.valueobject.UserId;

import java.util.Optional;

public interface UsersRepository {

    User create(UserId user);

    Optional<User> findById(UserId id);

    default User findByIdOrCreate(UserId id) {
        return findById(id)
                .orElseGet(() -> create(id));
    }

}
