package com.gad.ecommerce.payment.infrastructure.adapters.output.persistence;

import com.gad.ecommerce.payment.application.ports.output.PaymentGatewayPort;
import com.gad.ecommerce.payment.domain.model.Payment;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;

@Slf4j
@Component
public class MercadoPagoAdapter implements PaymentGatewayPort {
    @Value("${mercadopago.acess-token}")
    private String accessToken;

    @PostConstruct
    public void init() {
        MercadoPagoConfig.setAccessToken(accessToken);
    }

    @Override
    public Payment generatePaymentPreference(Payment payment, String title, String email) {
        try {
            PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                    .title(title)
                    .quantity(1)
                    .unitPrice(payment.getAmount())
                    .currencyId("PEN")
                    .build();

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://www.google.com/")
                    .failure("https://www.google.com/")
                    .pending("https://www.google.com/")
                    .build();

            PreferencePayerRequest payer = PreferencePayerRequest.builder()
                    .email(email)
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(Collections.singletonList(itemRequest))
                    .backUrls(backUrls)
                    .payer(payer)
                    .autoReturn("approved")
                    .externalReference(payment.getId().toString())
                    .build();

            PreferenceClient client = new PreferenceClient();
            Preference preference = client.create(preferenceRequest);

            payment.setTransactionId(preference.getId());
            payment.setPaymentUrl(preference.getSandboxInitPoint());

            return payment;
        } catch (MPApiException apiException) {
            // ERROR DE LA API (Datos inválidos, 400, 401, etc)
            log.error("MercadoPago API Error. Status: {}", apiException.getStatusCode());
            log.error("Response body: {}", apiException.getApiResponse().getContent()); // <--- AQUÍ ESTÁ LA CLAVE
            throw new RuntimeException("Error en API MercadoPago: " + apiException.getApiResponse().getContent());

        } catch (MPException mpException) {
            // ERROR DE TRANSPORTE (Sin internet, timeout)
            log.error("MercadoPago Transport Error", mpException);
            throw new RuntimeException("Error de conexión con MercadoPago");
        } catch (Exception e) {
            log.error("Unknown Error", e);
            throw new RuntimeException("Error general al crear preferencia");
        }
    }
}
