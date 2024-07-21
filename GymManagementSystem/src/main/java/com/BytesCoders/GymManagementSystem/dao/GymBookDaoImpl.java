package com.BytesCoders.GymManagementSystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.BytesCoders.GymManagementSystem.bean.GymBook;
@Service
@Repository
public class GymBookDaoImpl implements GymBookDao {
	
	@Autowired
	private GymBookRepository repository;
	

	@Override
	public void save(GymBook gymBook) {
		repository.save(gymBook);

	}

	@Override
	public Long generateBookingid() {
	 Long newId=repository.findLastBookingId();
	 if(newId==null) {
		 newId=10000L;
	 }else {
		 newId=newId+1L;
	 }
		return newId;
	}

	@Override
	public List<GymBook> getBookList() {
		
		return repository.findAll();
	}

	@Override
	public GymBook findBookInfoById(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public void deleteById(Long id) {
	repository.deleteById(id);
		
	}

	 @Override
	    public List<GymBook> getEntitiesByUsername(String username) {
	        return repository.findByUsername(username); // Assuming findByUsername is defined in GymBookRepository
	    }

	 @PersistenceContext
	  private EntityManager entityManager;

    @Override
    public boolean existsBySlotIdAndUsername(Long slotId, String username) {
        String hql = "SELECT COUNT(g) FROM GymBook g WHERE g.slotId = :slotId AND g.username = :username";
        Query query = entityManager.createQuery(hql);
        query.setParameter("slotId", slotId);
        query.setParameter("username", username);
        
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

	
	
}
