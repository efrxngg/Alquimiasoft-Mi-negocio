package com.alquimiasoft.minegocio.controller;

import com.alquimiasoft.minegocio.dto.*;
import com.alquimiasoft.minegocio.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.*;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Validated
public class CustomerRestContoller {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@Valid @RequestBody CustomerCreateDTO customerCreateDTO) {
        return status(HttpStatus.CREATED).body(customerService.saveCustomerWithAddress(customerCreateDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> findAllCustomersByIDNumberOrName(
            @RequestParam(required = false, defaultValue = "") String identificationNumber,
            @RequestParam(required = false, defaultValue = "") String name) {
        List<CustomerDTO> customerDTOS = customerService.findAllCustomerstByIdentityNumberOrName(identificationNumber, name);

        if (customerDTOS.isEmpty())
            return noContent().build();

        return ok().body(customerDTOS);
    }

    @PutMapping("/{id}/addresses")
    public ResponseEntity<ResponseMessage<AddressUpdateDTO>> updateAddresses(@PathVariable String id, @Valid @RequestBody AddressCreateDTO addressCreateDTO) {
        var addressUpdateDTO = customerService.saveNewAddressForCustomer(id, addressCreateDTO);
        return ok().body(new ResponseMessage<>("¡Dirección registrada exitosamente!", addressUpdateDTO));
    }

    @PutMapping("/only")
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerUpdateDTO customerUpdateDTO) {
        return ok().body(customerService.updateCustomerInformationOnly(customerUpdateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage<Void>> deleteCustomerById(@PathVariable String id) {
        if (!customerService.deleteCustomerById(id))
            return notFound().build();

        return ok().body(new ResponseMessage<>("Cliente eliminado exitosamente."));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<AddressUpdateDTO>> findAllAddressByCustomerId(@PathVariable String id) {
        List<AddressUpdateDTO> address = customerService.findAllAddressByCustomerId(id);

        if (address.isEmpty())
            return status(HttpStatus.NOT_FOUND).build();

        return ok().body(address);
    }
}
