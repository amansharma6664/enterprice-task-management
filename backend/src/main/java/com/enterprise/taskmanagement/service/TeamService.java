package com.enterprise.taskmanagement.service;
  
import com.enterprise.taskmanagement.dto.TeamDTO;
import com.enterprise.taskmanagement.entity.Team;
import com.enterprise.taskmanagement.entity.User;
import com.enterprise.taskmanagement.exception.BadRequestException;
import com.enterprise.taskmanagement.exception.ResourceNotFoundException;
import com.enterprise.taskmanagement.repository.TeamRepository;
import com.enterprise.taskmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeamService {
    
    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public List<TeamDTO> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public TeamDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
        return convertToDTO(team);
    }
    
    public List<TeamDTO> getTeamsByMember(Long userId) {
        return teamRepository.findTeamsByMemberId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public TeamDTO createTeam(Team team) {
        if (teamRepository.existsByName(team.getName())) {
            throw new BadRequestException("Team name already exists");
        }
        
        Team savedTeam = teamRepository.save(team);
        return convertToDTO(savedTeam);
    }
    
    public TeamDTO updateTeam(Long id, Team teamDetails) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
        
        if (teamDetails.getName() != null && !teamDetails.getName().equals(team.getName())) {
            if (teamRepository.existsByName(teamDetails.getName())) {
                throw new BadRequestException("Team name already exists");
            }
            team.setName(teamDetails.getName());
        }
        
        if (teamDetails.getDescription() != null) {
            team.setDescription(teamDetails.getDescription());
        }
        
        Team updatedTeam = teamRepository.save(team);
        return convertToDTO(updatedTeam);
    }
    
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + id));
        teamRepository.delete(team);
    }
    
    public TeamDTO addMemberToTeam(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        team.getMembers().add(user);
        Team updatedTeam = teamRepository.save(team);
        return convertToDTO(updatedTeam);
    }
    
    public TeamDTO removeMemberFromTeam(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team not found with id: " + teamId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        
        team.getMembers().remove(user);
        Team updatedTeam = teamRepository.save(team);
        return convertToDTO(updatedTeam);
    }
    
    private TeamDTO convertToDTO(Team team) {
        Set<Long> memberIds = team.getMembers().stream()
                .map(User::getId)
                .collect(Collectors.toSet());
        
        return new TeamDTO(
            team.getId(),
            team.getName(),
            team.getDescription(),
            memberIds,
            team.getCreatedAt(),
            team.getUpdatedAt()
        );
    }
}
