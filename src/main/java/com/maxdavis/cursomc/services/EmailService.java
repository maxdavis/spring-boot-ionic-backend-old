package com.maxdavis.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.maxdavis.cursomc.domain.Pedido;

/**
 * 
 * @author max
 *
 */
public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);	
	
}
