package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.dto.CustomerCreateDTO;
import com.alquimiasoft.minegocio.dto.CustomerDTO;
import com.alquimiasoft.minegocio.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerRestContoller {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@RequestBody CustomerCreateDTO customerCreateDTO) {
        return status(HttpStatus.CREATED).body(customerService.saveCustomerWithAddress(customerCreateDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> findAllCustomersByIDNumberOrName(
            @RequestParam(required = false) String identificationNumber,
            @RequestParam(required = false) String name) {
        List<CustomerDTO> customerDTOS = customerService.findAllCustomerstByIdentityNumberOrName(identificationNumber, name);

        if (customerDTOS.isEmpty())
            return noContent().build();

        return ok().body(customerDTOS);
    }
}
