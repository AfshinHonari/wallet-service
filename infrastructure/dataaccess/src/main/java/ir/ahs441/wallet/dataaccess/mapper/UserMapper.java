package ir.ahs441.wallet.dataaccess.mapper;

import ir.ahs441.wallet.dataaccess.entity.UserEntity;
import ir.ahs441.wallet.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    @Mapping(target = "id", source = "id.value")
    @Mapping(target = "transactions", ignore = true)
    UserEntity mapToUserEntity(User user);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "transactions", ignore = true)
    User mapToUser(UserEntity entity);



}
