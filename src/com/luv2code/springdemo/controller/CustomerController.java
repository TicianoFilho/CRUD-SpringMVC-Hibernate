package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import com.luv2code.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String lisCustomers(@RequestParam("sort") String sort, Model cosModel) {
		
		List<Customer> customers = null;
		
		if (sort != null && !sort.isEmpty()) {
			customers = customerService.getCustomers(Integer.parseInt(sort));
		} else {
			customers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		cosModel.addAttribute("customers", customers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showForm(Model model) {
		
		//Create the model attribute to bind form data
		Customer theCustomer = new Customer();
		model.addAttribute("customer", theCustomer);
		
		return "customer-form";
	} 
	
	@PostMapping("/saveCustomer")
	public String save(@ModelAttribute("customer") Customer customer) {
			
		//save customer using our service
		customerService.saveCustomer(customer);
		
		return "redirect:/customer/list?sort=";
	}
	
	@GetMapping("/showFormForUpdate")
	public String update(@RequestParam("customerId") int customerId, Model model) {
		
		//get the customer from our service
		Customer customer = customerService.getCustomer(customerId);
		
		//set the customer as a model attribute to pre-populate the form. (OBS: the attribute name which is between those double quotes "customer" must be the same of the ModelAttribue of the form in the JSP page)
		model.addAttribute("customer", customer);
		
		//send over to our form
		return "customer-form";
	}
	

	@PostMapping("/updateCustomer")
	public String update(@ModelAttribute("customer") Customer customer) {
		
		customerService.updateCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {
		
		customerService.deleteCustomer(customerId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("searchName") String searchName, Model model) {
		
		List<Customer> customers = customerService.searchCustomer(searchName);
		
		model.addAttribute("customers", customers);
		System.out.println(customers);
		
		return "list-customers";
	}
	
}






