package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jobpost")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;
    @GetMapping("/get")
    public ResponseEntity getjobPost(){
        return ResponseEntity.status(200).body(jobPostService.getAllJobPost());
    }
    @PostMapping("/add")
    public ResponseEntity addJobPost(@Valid @RequestBody JobPost jobPost, Errors errors){
        if(errors.hasErrors()){
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        jobPostService.addJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse( "job Post added successfully"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateJobPost(@PathVariable Integer id,@Valid@RequestBody JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isupdate = jobPostService.updateJobPost(id,jobPost);
        if (isupdate) {
            return ResponseEntity.status(200).body(new ApiResponse("update success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("update fail, not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobPost(@PathVariable Integer id) {
        boolean isdelete = jobPostService.deleteJobPost(id);
        if (isdelete) {
            return ResponseEntity.status(200).body(new ApiResponse("delete success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("delete fail, not found"));

    }
}
