package com.example.repositorie;

import com.example.entity.RequestLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    @Query("SELECT SUM(t.trackTime) FROM RequestLog t WHERE t.requestMethod = :method")
    long sumTrackTimeByRequestMethod(RequestMethod method);
}
