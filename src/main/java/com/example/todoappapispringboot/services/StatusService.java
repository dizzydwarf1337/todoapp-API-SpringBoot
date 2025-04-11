package com.example.todoappapispringboot.services;

import com.example.todoappapispringboot.dtos.Status.CreateStatusDto;
import com.example.todoappapispringboot.dtos.Status.EditStatusDto;
import com.example.todoappapispringboot.dtos.Status.StatusDto;
import com.example.todoappapispringboot.models.Status;
import com.example.todoappapispringboot.repositories.StatusRepository;
import com.example.todoappapispringboot.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StatusService
{
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;

    public StatusService(StatusRepository statusRepository, UserRepository userRepository) {
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
    }


    public StatusDto GetStatusById(UUID statusId){
        var status = statusRepository.findById(statusId).orElseThrow(()-> new RuntimeException("Status not found"));
        return new StatusDto(status);
    }

    public List<StatusDto> GetStatusesByUserId(UUID userId) {
        var user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        var statuses = statusRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Statuses not found"));
        return statuses.stream().map(StatusDto::new).collect(Collectors.toList());
    }
    public Status CreateStatus(CreateStatusDto createStatusDto) {
        var user = userRepository.findById(UUID.fromString(createStatusDto.getUserId())).orElseThrow();
        Status status = new Status();
        status.setTitle(createStatusDto.getTitle());
        status.setUser(user);
        statusRepository.save(status);
        return status;
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
