package org.example.conf;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.security.Security;

@Configuration
public class BouncyCastleConfig {

    @PostConstruct
    public void addBouncyCastleProvider() {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
}