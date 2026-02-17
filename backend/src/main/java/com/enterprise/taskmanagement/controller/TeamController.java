package com.enterprise.taskmanagement.controller;
 
import com.enterprise.taskmanagement.dto.TeamDTO;
import com.enterprise.taskmanagement.entity.Team;
import com.enterprise.taskmanagement.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
@CrossOrigin(origins = "*")
public class TeamController {
    
    @Autowired
    private TeamService teamService;
    
    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getTeamById(id));
    }
    
    @GetMapping("/member/{userId}")
    public ResponseEntity<List<TeamDTO>> getTeamsByMember(@PathVariable Long userId) {
        return ResponseEntity.ok(teamService.getTeamsByMember(userId));
    }
    
    @PostMapping
    public ResponseEntity<TeamDTO> createTeam(@RequestBody Team team) {
        return new ResponseEntity<>(teamService.createTeam(team), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TeamDTO> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return ResponseEntity.ok(teamService.updateTeam(id, team));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{teamId}/members/{userId}")
    public ResponseEntity<TeamDTO> addMemberToTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        return ResponseEntity.ok(teamService.addMemberToTeam(teamId, userId));
    }
    
    @DeleteMapping("/{teamId}/members/{userId}")
    public ResponseEntity<TeamDTO> removeMemberFromTeam(@PathVariable Long teamId, @PathVariable Long userId) {
        return ResponseEntity.ok(teamService.removeMemberFromTeam(teamId, userId));
    }
}
