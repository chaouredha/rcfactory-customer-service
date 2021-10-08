package org.rcfactory.customerservice.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Map;

import org.rcfactory.customerservice.controllers.dto.CustomerRequestDTO;
import org.rcfactory.customerservice.controllers.dto.CustomerResponseDTO;
import org.rcfactory.customerservice.services.CustomerService;
import org.springframework.beans.factory.annotation.Value;
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
    private final String username;
    private final String password;
    private final String usernameCustomer;
    private final String passwordCustomer;

    public CustomerController(CustomerService customerService,
            @Value("${username.application}") String username,
            @Value("${password.application}") String password,
            @Value("${username.customer}") String usernameCustomer,
            @Value("${password.customer}") String passwordCustomer) {
        this.customerService = customerService;
        this.username = username;
        this.password = password;
        this.usernameCustomer = usernameCustomer;
        this.passwordCustomer = passwordCustomer;
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

    @GetMapping("/config")
    public Map<String, String> config() {
        return Map.of("username-application", username,
                "password-application", password,
                "username-customer-service", usernameCustomer,
                "passsword-customer-service", passwordCustomer,
                "threadName", Thread.currentThread().getName());
    }
}
