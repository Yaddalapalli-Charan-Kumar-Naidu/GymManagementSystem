package com.BytesCoders.GymManagementSystem.service;

import com.BytesCoders.GymManagementSystem.bean.Feedback;
import com.BytesCoders.GymManagementSystem.dao.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
