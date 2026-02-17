package com.enterprise.taskmanagement.repository;
 
import com.enterprise.taskmanagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByAssignedToId(Long userId);
    
    List<Task> findByCreatedById(Long userId);
    
    List<Task> findByStatus(Task.Status status);
    
    List<Task> findByPriority(Task.Priority priority);
    
    List<Task> findByTeamId(Long teamId);
    
    @Query("SELECT t FROM Task t WHERE t.assignedTo.id = :userId AND t.status = :status")
    List<Task> findByAssignedToIdAndStatus(@Param("userId") Long userId, @Param("status") Task.Status status);
    
    @Query("SELECT t FROM Task t WHERE t.dueDate BETWEEN :startDate AND :endDate")
    List<Task> findTasksDueBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    @Query("SELECT t FROM Task t WHERE t.team.id = :teamId AND t.status = :status")
    List<Task> findByTeamIdAndStatus(@Param("teamId") Long teamId, @Param("status") Task.Status status);
}
