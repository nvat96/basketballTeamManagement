package com.axonactive.basketball.repositories;

import com.axonactive.basketball.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team,String> {
    @Query("FROM Team " +
            "WHERE name LIKE CONCAT ('%', :name, '%')")
    List<Team> findByNameLike(@Param("name") String name);
}
