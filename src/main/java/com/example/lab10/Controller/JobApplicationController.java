package com.example.lab10.Controller;

import com.example.lab10.Api.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/Japplication")
@RequiredArgsConstructor
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;
    @GetMapping("/get")
    public ResponseEntity getjobApplication(){
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplication());
    }
    @PostMapping("/add")
    public ResponseEntity applyJobApplication(@Valid @RequestBody JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            String errorMessage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(errorMessage);
        }
        String isaply= jobApplicationService.applyForJob(jobApplication);
        if(isaply.equals("true")){
            return ResponseEntity.status(200).body(new ApiResponse("job Application apply successfully"));
        }
        if (isaply.equals("1")){
            return ResponseEntity.status(400).body(new ApiResponse("user id not exist"));
        }
        if (isaply.equals("2")){
            return ResponseEntity.status(400).body(new ApiResponse("job id not exist"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("apply filed"));

    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateJobApplication(@PathVariable Integer id,@Valid@RequestBody JobApplication jobApplication, Errors errors) {
        if (errors.hasErrors()) {
            String msg = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(msg);
        }
        boolean isupdate = jobApplicationService.updateJobApplication(id, jobApplication);
        if (isupdate) {
            return ResponseEntity.status(200).body(new ApiResponse("update success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("update fail, not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity withdrowJobApplication(@PathVariable Integer id) {
        boolean isdelete = jobApplicationService.withdrawApplication(id);
        if (isdelete) {
            return ResponseEntity.status(200).body(new ApiResponse("withdrow success"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("withdrow faild, job Application not found"));

    }


}
