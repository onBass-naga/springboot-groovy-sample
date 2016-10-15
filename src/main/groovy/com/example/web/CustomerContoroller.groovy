package com.example.web

import com.example.domain.Customer
import com.example.service.CustomerService
import groovy.transform.Canonical
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Controller
@RequestMapping("customers")
class CustomerContoroller {

    @Autowired
    CustomerService customerService

    @ModelAttribute
    CustomerForm customerForm() {
        return new CustomerForm()
    }

    @GetMapping
    String list(Model model, @PageableDefault Pageable pageable) {
        List<Customer> customers = customerService.findAll(pageable).getContent()
        model.addAttribute("customers", customers)
        return "customers/list"
    }

    @PostMapping(path = "create")
    String create(@Validated CustomerForm form,
                  BindingResult result,
                  Model model,
                  @PageableDefault Pageable pageable) {

        if (result.hasErrors()) {
            return list(model, pageable)
        }

        Customer customer = new Customer()
        BeanUtils.copyProperties(form, customer)
        customerService.save(customer)

        return "redirect:/customers"
    }

    @GetMapping(path = "edit", params = "form")
    String editForm(@RequestParam Integer id, CustomerForm form) {
        Customer customer = customerService.findOne(id)
        BeanUtils.copyProperties(customer, form)
        return "customers/edit"
    }

    @PostMapping(path = "edit")
    String edit(@RequestParam Integer id,
                @Validated CustomerForm form,
                BindingResult result) {

        if (result.hasErrors()) {
            return editForm(id, form)
        }

        // BeanUtils#copyProperties を使うと id が設定できなくなる
        Customer customer = new Customer(id, form.firstName, form.lastName)
        customerService.save(customer)

        return "redirect:/customers"
    }

    @GetMapping(path = "edit", params = "goToTop")
    String goToTop() {
        return "redirect:/customers"
    }

    @PostMapping(path = "delete")
    String delete(@RequestParam Integer id) {
        customerService.delete(id);
        return "redirect:/customers"
    }
}


@Canonical
class CustomerForm {
    @NotNull
    @Size(min = 1, max = 127)
    String firstName

    @NotNull
    @Size(min = 1, max = 127)
    String lastName
}