package com.example.todoappapispringboot.controllers;

import com.example.todoappapispringboot.dtos.ApiResponse;
import com.example.todoappapispringboot.dtos.Status.CreateStatusDto;
import com.example.todoappapispringboot.dtos.Status.EditStatusDto;
import com.example.todoappapispringboot.dtos.Status.StatusDto;
import com.example.todoappapispringboot.services.StatusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/statuses")
public class StatusController extends BaseController {

    public final StatusService statusService;
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }


    @GetMapping("/{statusId}")
    public ResponseEntity<ApiResponse<StatusDto>> getStatusById(@PathVariable String statusId){
        try{
            var status = statusService.GetStatusById(UUID.fromString(statusId));
            return handleResponse(ApiResponse.success(status));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<StatusDto>>> GetStatusesByUserId(@PathVariable String userId) {
        try{
            var statuses = statusService.GetStatusesByUserId(UUID.fromString(userId));
            return handleResponse(ApiResponse.success(statuses));
        }
        catch(Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));

        }
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<String>> CreateStatus(@RequestBody CreateStatusDto createStatusDto) {
        try{
            statusService.CreateStatus(createStatusDto);
            return handleResponse(ApiResponse.success("Status created successfully"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
    @DeleteMapping("/delete/{statusId}")
    public ResponseEntity<ApiResponse<String>> DeleteStatus(@PathVariable String statusId) {
        try{
            statusService.DeleteStatus(UUID.fromString(statusId));
            return handleResponse(ApiResponse.success("Status deleted successfully"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
    @PutMapping("/edit")
    public ResponseEntity<ApiResponse<String>> UpdateStatus(@RequestBody EditStatusDto editStatusDto) {
        try{
            statusService.EditStatus(editStatusDto);
            return handleResponse(ApiResponse.success("Status updated successfully"));
        }
        catch (Exception e){
            return handleResponse(ApiResponse.failure(e.getMessage()));
        }
    }
}
