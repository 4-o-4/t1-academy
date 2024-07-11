package com.example.repositorie;

import com.example.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    @Query("SELECT SUM(t.trackTime) FROM Time t WHERE t.requestMethod = :method")
    long getTotalTrackTimeByMethod(RequestMethod method);
}
