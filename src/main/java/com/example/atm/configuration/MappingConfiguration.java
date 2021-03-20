package com.example.atm.configuration;

import com.example.atm.entity.BankAccountHolder;
import com.example.atm.entity.BankTransaction;
import com.example.atm.model.BankAccountHolderModel;
import com.example.atm.model.BankTransactionFullModel;
import com.example.atm.model.BankTransactionModel;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.github.dozermapper.core.loader.api.TypeMappingOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MappingConfiguration {


    @Bean
    public Mapper entityModelMapper() {

        Mapper mapper = DozerBeanMapperBuilder.create().withMappingBuilder(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(type(BankAccountHolder.class).mapEmptyString(true), type(BankAccountHolderModel.class),
                        TypeMappingOptions.wildcardCaseInsensitive(true));
                mapping(type(BankTransaction.class).mapEmptyString(true), type(BankTransactionModel.class),
                        TypeMappingOptions.wildcardCaseInsensitive(true));

            }
        }).build();
        return mapper;
    }


}
