package com.example.api;

import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

public class MR {

    public static void migrate(DataSource ds) {
        Flyway flyway = Flyway.configure()
                .dataSource(ds)
                .locations("classpath:db/migration").baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}

