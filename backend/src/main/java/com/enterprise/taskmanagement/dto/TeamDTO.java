package com.enterprise.taskmanagement.dto;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    private Long id;
    private String name;
    private String description;
    private Set<Long> memberIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
