package ir.ahs441.wallet.dataaccess.adapter;

import ir.ahs441.wallet.dataaccess.entity.UserEntity;
import ir.ahs441.wallet.dataaccess.mapper.UserMapper;
import ir.ahs441.wallet.dataaccess.repository.UserJpaRepository;
import ir.ahs441.wallet.domain.entity.User;
import ir.ahs441.wallet.domain.repository.UsersRepository;
import ir.ahs441.wallet.domain.valueobject.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UsersRepositoryImpl implements UsersRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User create(UserId id) {
        return userMapper.mapToUser(userJpaRepository
                .save(new UserEntity(id.getValue(), BigDecimal.ZERO, Set.of(), 0L)));
    }

    @Override
    public Optional<User> findById(UserId id) {
        return userJpaRepository.findById(id.getValue())
                .map(userMapper::mapToUser);
    }
}
