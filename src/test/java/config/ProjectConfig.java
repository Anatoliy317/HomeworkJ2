package config;

import org.aeonbits.owner.Config;


@Config.Sources("classpath:config/${environment}.properties")
public interface ProjectConfig extends Config {

    @Key("firstName")
    @DefaultValue("I")
    String firstName();
    @Key("family")
    String lastName();
    String email();


}
