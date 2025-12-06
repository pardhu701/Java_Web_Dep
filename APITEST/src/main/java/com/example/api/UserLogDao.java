package com.example.api;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface UserLogDao {

    @SqlUpdate("""
        INSERT INTO user_logs (user_id, action, event_time)
        VALUES (:id, :action, now())
    """)
    void insert(@Bind("id") int id, @Bind("action") String action);

    @SqlQuery("""
        SELECT action FROM user_logs
        WHERE user_id = :id
        ORDER BY event_time DESC
        LIMIT 5
    """)
    List<String> getRecentActions(@Bind("id") int id);

//    @SqlQuery("""
//        SELECT * FROM patient
//        WHERE id = :id
//
//    """)
//    String get(@Bind("id") int id);

    @SqlQuery("SELECT * FROM patient WHERE id = :id")
    @RegisterBeanMapper(Patient.class)
    Patient getPatient(@Bind("id") int id);

}

