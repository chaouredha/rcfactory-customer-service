package org.rcfactory.customerservice.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.rcfactory.customerservice.controllers.dto.CustomerRequestDTO;
import org.rcfactory.customerservice.controllers.dto.CustomerResponseDTO;
import org.rcfactory.customerservice.services.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<CustomerResponseDTO> customers() {
        return customerService.customers();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO customer(@PathVariable Long id) {
        return customerService.customer(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO) {
        return customerService.save(customerRequestDTO);
    }
}
