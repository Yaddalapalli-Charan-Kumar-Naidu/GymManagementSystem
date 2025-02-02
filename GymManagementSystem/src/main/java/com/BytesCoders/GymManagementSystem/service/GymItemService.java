package com.BytesCoders.GymManagementSystem.service;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BytesCoders.GymManagementSystem.bean.GymItem;
import com.BytesCoders.GymManagementSystem.bean.Item;
import com.BytesCoders.GymManagementSystem.bean.SlotItem;
import com.BytesCoders.GymManagementSystem.bean.SlotItemEmbed;
import com.BytesCoders.GymManagementSystem.dao.GymItemDao;
import com.BytesCoders.GymManagementSystem.dao.GymItemRepository;
import com.BytesCoders.GymManagementSystem.dao.SlotItemDao;

@Service
public class GymItemService {
	@Autowired
	private GymItemDao gymItemDao;
	@Autowired
	private SlotItemDao slotItemDao;
	
	
	public List<Item> getItemsList (Long slotId) { 
		List<Item> itemList=new ArrayList<>();
		List<GymItem> gymList=gymItemDao.displayAllItems();
		for (GymItem gym:gymList) {
		Item item=new Item (gym);
		SlotItemEmbed embed=new SlotItemEmbed (slotId, gym.getItemId());
		Integer seatBooked=slotItemDao.findSeatBookedById(embed);
		if (seatBooked==null)
		seatBooked=0;
		int seatVacant=gym.getTotalSeat ()-seatBooked.intValue();
		item.setSeatVacant (seatVacant);
		itemList.add(item);
		}
		return itemList;
		}


	public void addNewItemToSlots(Long itemId) {

         Set<SlotItemEmbed> embedSet=slotItemDao.findAllEmbeds();
         Set<Long> itemSet=new HashSet<>();
         Set<Long> slotSet=new HashSet<>();
         
         for (SlotItemEmbed embed: embedSet) {
        	 itemSet.add(embed.getItemId()); 
        	slotSet.add(embed.getSlotId());
        	}
       
		if (itemSet.contains(itemId) ==false) {
        	 for (Long slotId: slotSet) {
        	 SlotItemEmbed embed=new SlotItemEmbed (slotId, itemId); 
        	 SlotItem slotItem = new SlotItem (embed);
        	 slotItemDao.save (slotItem);
        	 }
        	 }
		
	}
	 @Autowired
	    private GymItemRepository gymItemRepository;

	    public List<GymItem> getAllItems() {
	        return gymItemRepository.findAll();
	    }

	    public void updateItemCount(Long itemId, int count) {
	        GymItem item = gymItemRepository.findById(itemId).orElseThrow();
	        item.setCount(count);
	        gymItemRepository.save(item);
	    }


	
	

}
