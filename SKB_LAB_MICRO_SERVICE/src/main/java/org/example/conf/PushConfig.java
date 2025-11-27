package org.example.conf;

import nl.martijndwars.webpush.PushService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.GeneralSecurityException;

@Configuration
public class PushConfig {

    @Value("${notify.public.key}")
    private String publicKey;

    @Value("${notify.private.key}")
    private String privateKey;

    @Bean
    public PushService pushService() throws GeneralSecurityException {
        PushService pushService = new PushService();
//        pushService.setPublicKey(publicKey);
//        pushService.setPrivateKey(privateKey);
        return pushService;
    }
}
