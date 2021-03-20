package com.example.atm.controller;


import com.example.atm.exception.DataNotSaved;
import com.example.atm.model.AtmLocator;
import com.example.atm.model.BankTransactionFullModel;
import com.example.atm.model.BankTransactionModel;
import com.example.atm.service.AtmService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/atm")
@Slf4j
@Api("Atm")
public class AtmController {


    private AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @ApiOperation(value = "Retrieve information from https://www.ing.nl/api/locator/atms/")
    @GetMapping(value = "/atmService")
    public ResponseEntity<List<AtmLocator>> responseEntity() throws JsonProcessingException {
        List<AtmLocator> all = atmService.findAll();
        return ResponseEntity.ok().body(all);
    }

    @ApiOperation(value = "Create transaction for atm")
    @PostMapping(value = "")
    public ResponseEntity<BankTransactionFullModel> createTransaction(@RequestBody @Valid BankTransactionModel bankTransactionModel) throws DataNotSaved {
        BankTransactionFullModel save = this.atmService.save(bankTransactionModel);
        return ResponseEntity.ok().body(save);
    }

    @ApiOperation(value = "Get transaction for atm")
    @GetMapping(value = "")
    public ResponseEntity<BankTransactionFullModel> find(@RequestParam(value = "transactionId", required = true) String transactionId) {
        BankTransactionFullModel findModel = this.atmService.findTransaction(Long.valueOf(transactionId));
        return ResponseEntity.ok().body(findModel);

    }


    @ApiOperation(value = "Update transaction for atm")
    @PutMapping(value = "")
    public ResponseEntity<BankTransactionFullModel> update(@RequestBody BankTransactionFullModel bankTransactionFullModel) throws DataNotSaved {
        BankTransactionFullModel update = this.atmService.update(bankTransactionFullModel);
        return ResponseEntity.ok().body(update);
    }




    @ApiOperation(value = "Delete transaction for atm")
    @DeleteMapping(value = "/{transactionId}")
    public ResponseEntity delete(@PathVariable(value = "transactionId", required = true) String transactionId) {

        this.atmService.delete(Long.valueOf(transactionId));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }



}
