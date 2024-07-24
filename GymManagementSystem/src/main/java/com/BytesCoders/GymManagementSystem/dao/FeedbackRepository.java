package com.BytesCoders.GymManagementSystem.dao;

import com.BytesCoders.GymManagementSystem.bean.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
