package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/local.properties",
        "classpath:config/${environment}.properties"})
public interface ProjectConfig extends Config {
    @Key("web.url")
    String webUrl();
    @Key("api.url")
    String apiUrl();
    @Key("api.port")
    String apiPort();


}
