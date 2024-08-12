package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private  final UserRepository userRepository;

    private final JobPostRepository jobPostRepository;

    public List<JobApplication> getAllJobApplication(){
        return jobApplicationRepository.findAll();
    }
    public String applyForJob(JobApplication jobApplication){
        User user = userRepository.getReferenceById(jobApplication.getUserId());
        if(user == null){
            return "1";

        }
        JobPost jobPost = jobPostRepository.getReferenceById(jobApplication.getJobPostId());
        if(jobPost == null){
            return "2";
        }
        jobApplicationRepository.save(jobApplication);
        return "true";
    }
    public boolean updateJobApplication(Integer id ,JobApplication jobApplication){
        JobApplication jobApplication1 = jobApplicationRepository.getReferenceById(id);
        if(jobApplication1 != null){

            jobApplication1.setJobPostId(jobApplication.getJobPostId());
            jobApplication1.setUserId(jobApplication.getUserId());
            jobApplicationRepository.save(jobApplication1);
            return true;
        }
        return false;
    }
    public boolean withdrawApplication(Integer id){
        JobApplication jobApplication = jobApplicationRepository.getReferenceById(id);
        if(jobApplication != null){
            jobApplicationRepository.delete(jobApplication);
            return true;
        }


return false;
    }




    }
