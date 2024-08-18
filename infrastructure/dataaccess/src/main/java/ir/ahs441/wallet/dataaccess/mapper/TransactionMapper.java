package ir.ahs441.wallet.dataaccess.mapper;

import ir.ahs441.wallet.dataaccess.entity.TransactionEntity;
import ir.ahs441.wallet.domain.entity.Transaction;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {UserMapper.class}
)
public interface TransactionMapper {

    @Mapping(target = "id", source = "id.value")
    TransactionEntity mapToTransactionEntity(Transaction transaction);

    @Mapping(target = "id.value", source = "id")
    Transaction mapToTransaction(TransactionEntity entity);

    @ValueMappings({
            @ValueMapping(source = "DEPOSIT", target = "DEPOSIT"),
            @ValueMapping(source = "WITHDRAW", target = "WITHDRAW"),
            @ValueMapping(source = MappingConstants.ANY_REMAINING, target = MappingConstants.THROW_EXCEPTION)
    })
    TransactionEntity.TransactionType mapTransactionType(Transaction.TransactionType transactionType);

    @InheritInverseConfiguration
    Transaction.TransactionType mapTransactionType(TransactionEntity.TransactionType transactionType);

}
