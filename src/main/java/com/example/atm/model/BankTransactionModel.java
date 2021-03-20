package com.example.atm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Bank transactions for atm")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankTransactionModel {

    @ApiModelProperty(value = "amount of transaction")
    @Min(value = 1)
    protected double amount;

    @ApiModelProperty("device information device id")
    @NotBlank
    protected String device_information;

    @Valid
    @NotNull
    @ApiModelProperty("Information about bank holder")
    protected BankAccountHolderModel bankAccountHolder;

}
