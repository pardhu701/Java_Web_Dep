package com.example.api;

import org.glassfish.jersey.server.ResourceConfig;
import jakarta.ws.rs.ApplicationPath;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationPath("/")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("com.example.api");
  register(org.glassfish.jersey.jackson.JacksonFeature.class);

        // ---------- YOUR STARTUP LOGIC ----------
        LogConfig.configure();                // logging config
        DataSource ds = DataSourceFactory.create();   // create datasource
        MR.migrate(ds);
    }
}
