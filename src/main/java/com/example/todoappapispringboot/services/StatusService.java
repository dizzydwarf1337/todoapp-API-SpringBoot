package com.example.todoappapispringboot.services;

import com.example.todoappapispringboot.dtos.Status.CreateStatusDto;
import com.example.todoappapispringboot.dtos.Status.EditStatusDto;
import com.example.todoappapispringboot.models.Status;
import com.example.todoappapispringboot.repositories.StatusRepository;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StatusService
{
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public StatusService(StatusRepository statusRepository, UserRepository userRepository) {
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }
    public List<Status> GetStatusesByUserId(String userId){
        UUID id = UUID.fromString(userId);
        return statusRepository.GetStatusesByUserId(id);
    }
    public void CreateStatus(CreateStatusDto createStatusDto) {
        UUID id = UUID.randomUUID();
        var user = userRepository.findById(UUID.fromString(createStatusDto.getUserId())).orElseThrow();
        Status status = new Status();
        status.setId(id);
        status.setTitle(createStatusDto.getTitle());
        status.setUser(user);
    }
    public void DeleteStatus(UUID id) {
        var status = statusRepository.findById(id).orElseThrow();
        if(status.getTasks().isEmpty()){
            statusRepository.deleteById(id);
        }
        else throw new RuntimeException("Cannot delete status with tasks");
    }
    public void EditStatus(EditStatusDto editStatusDto){
        var status = statusRepository.findById(UUID.fromString(editStatusDto.getStatusId())).orElseThrow();
        status.setTitle(editStatusDto.getTitle());
        statusRepository.save(status);
    }

}
