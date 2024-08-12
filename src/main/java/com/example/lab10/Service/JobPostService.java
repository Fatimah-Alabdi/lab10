package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPost(){
        return jobPostRepository.findAll();
    }
    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }
    public boolean updateJobPost(Integer id ,JobPost jobPost){
        JobPost jobPost1 = jobPostRepository.getReferenceById(id);
        if(jobPost1 != null){
            jobPost1.setTitle(jobPost.getTitle());
            jobPost1.setDescription(jobPost.getDescription());
            jobPost1.setLocation(jobPost.getLocation());
            jobPost1.setSalary(jobPost.getSalary());
            jobPost1.setPostingDate(jobPost.getPostingDate());
            jobPostRepository.save(jobPost1);
            return true;
        }
        return false;
    }
    public boolean deleteJobPost(Integer id){
        JobPost jobPost = jobPostRepository.getReferenceById(id);
        if(jobPost != null){
            jobPostRepository.delete(jobPost);
            return true;
        }
        return false;
    }
}
