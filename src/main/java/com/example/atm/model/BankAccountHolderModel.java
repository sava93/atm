package com.example.atm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountHolderModel {

    @ApiModelProperty(dataType = "string", value = "account number of owner")
    @NotBlank
    private String accountNumber;
}
