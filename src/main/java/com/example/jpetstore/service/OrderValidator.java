package com.example.jpetstore.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.example.jpetstore.domain.Order;

/**
 * @author Juergen Hoeller
 * @since 01.12.2003
 * @modified by Changsup Park
 */
@Component
public class OrderValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return Order.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		validateCreditCard((Order) obj, errors);
		validateBillingAddress((Order) obj, errors);
		validateShippingAddress((Order) obj, errors);
	}

	public void validateCreditCard(Order order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "creditCard", "CCN_REQUIRED", "FAKE (!) credit card number required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "expiryDate", "EXPIRY_DATE_REQUIRED", "Expiry date is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cardType", "CARD_TYPE_REQUIRED", "Card type is required.");
		errors.setNestedPath("");
	}

	public void validateBillingAddress(Order order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billToFirstName", "FIRST_NAME_REQUIRED", "Billing Info: first name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billToLastName", "LAST_NAME_REQUIRED", "Billing Info: last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.address1", "ADDRESS_REQUIRED", "Billing Info: address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.city", "CITY_REQUIRED", "Billing Info: city is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.state", "STATE_REQUIRED", "Billing Info: state is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.zip", "ZIP_REQUIRED", "Billing Info: zip/postal code is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "billingAddress.country", "COUNTRY_REQUIRED", "Billing Info: country is required.");
		errors.setNestedPath("");
	}

	public void validateShippingAddress(Order order, Errors errors) {
		errors.setNestedPath("order");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipToFirstName", "FIRST_NAME_REQUIRED", "Shipping Info: first name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shipToLastName", "LAST_NAME_REQUIRED", "Shipping Info: last name is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.address1", "ADDRESS_REQUIRED", "Shipping Info: address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.city", "CITY_REQUIRED", "Shipping Info: city is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sshippingAddress.state", "STATE_REQUIRED", "Shipping Info: state is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.zip", "ZIP_REQUIRED", "Shipping Info: zip/postal code is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shippingAddress.country", "COUNTRY_REQUIRED", "Shipping Info: country is required.");
		errors.setNestedPath("");
	}
}