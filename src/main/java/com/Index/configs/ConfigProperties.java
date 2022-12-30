package com.Index.configs;


import com.Index.providers.ProviderConfigData;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "index")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConfigProperties {
    ProviderConfigData flutterWave;
    ProviderConfigData payStack;
}
