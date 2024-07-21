package com.BytesCoders.GymManagementSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.BytesCoders.GymManagementSystem.bean.GymBook;
import com.BytesCoders.GymManagementSystem.bean.GymItem;
import com.BytesCoders.GymManagementSystem.bean.Item;
import com.BytesCoders.GymManagementSystem.bean.Slot;
import com.BytesCoders.GymManagementSystem.bean.SlotItem;
import com.BytesCoders.GymManagementSystem.bean.SlotItemEmbed;
import com.BytesCoders.GymManagementSystem.dao.GymBookDao;
import com.BytesCoders.GymManagementSystem.dao.GymItemDao;
import com.BytesCoders.GymManagementSystem.dao.SlotDao;
import com.BytesCoders.GymManagementSystem.dao.SlotItemDao;
import com.BytesCoders.GymManagementSystem.exception.SeatNotAvailableException;
import com.BytesCoders.GymManagementSystem.exception.SlotAlreadyBookedException;
import com.BytesCoders.GymManagementSystem.service.GymItemService;
import com.BytesCoders.GymManagementSystem.service.GymUserService;

@RestController
public class GymController {
    
    @Autowired
    private GymItemDao gymItemDao;
    
    @Autowired
    private SlotDao slotDao;
    
    @Autowired
    private SlotItemDao slotItemDao;
    
    @Autowired
    private GymItemService itemService;
    
    @Autowired
    private GymUserService userService;
    
    @Autowired
    private GymBookDao gymBookDao;

    @GetMapping("/index")
    public ModelAndView showIndexPage() {
        String indexPage = "";
        String userType = userService.getType();
        if (userType.equalsIgnoreCase("Admin")) 
            indexPage = "index1";
        else if (userType.equalsIgnoreCase("Customer"))
            indexPage = "index2";
        return new ModelAndView(indexPage);
    }
    
    @GetMapping("/gymitem")
    public ModelAndView showItemEntryPage() {
        GymItem gymItem = new GymItem();
        Long newId = gymItemDao.generateItemId();
        gymItem.setItemId(newId);
        ModelAndView mv = new ModelAndView("gymItemEntryPage");
        mv.addObject("itemRecord", gymItem);
        return mv;
    }

    @PostMapping("/gymitem")
    public ModelAndView saveItem(@ModelAttribute("itemRecord") GymItem gymItem) {
        gymItemDao.saveNewItem(gymItem);
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/gymitems")
    public ModelAndView showItemReportPage() {
        List<GymItem> itemList = gymItemDao.displayAllItems();
        ModelAndView mv = new ModelAndView("gymItemReportPage");
        mv.addObject("itemList", itemList);
        return mv;
    }

    @GetMapping("/slot")
    public ModelAndView showSlotEntryPage() {
        Slot slot = new Slot();
        Long newId = slotDao.generateSlotId(); 
        slot.setSlotId(newId);
        ModelAndView mv = new ModelAndView("slotEntryPage");
        mv.addObject("slotRecord", slot);
        return mv;
    }

    @PostMapping("/slot")
    public ModelAndView saveSlot(@ModelAttribute("slotRecord") Slot slot) {
        slotDao.saveNewSlot(slot);
        List<GymItem> itemList = gymItemDao.displayAllItems();
        for(GymItem item : itemList) {
            SlotItemEmbed embed = new SlotItemEmbed(slot.getSlotId(), item.getItemId());
            SlotItem slotItem = new SlotItem(embed);
            slotItemDao.save(slotItem);
        }
        return new ModelAndView("redirect:/index");
    }

    @GetMapping("/slots")
    public ModelAndView showSlotReportPage() {
        List<Slot> slotList = slotDao.displayAllSlots();
        ModelAndView mv = new ModelAndView("slotReportPage");
        mv.addObject("slotList", slotList);
        return mv;
    }

    @GetMapping("/slot-book/{id}")
    public ModelAndView showSlotBookPage(@PathVariable Long id){
    	String fname="";
    	String userType=userService.getType();
    	if(userType.equalsIgnoreCase("Admin"))
    		fname="slotBookPage1";
    	else if(userType.equalsIgnoreCase("Customer"))
    		fname="slotBookPage2";
    	GymBook book=new GymBook();
    	Long newId=gymBookDao.generateBookingid();
    	book.setBookingId(newId);
    	Slot slot=slotDao.findSlotById(id);
    	List<Item> itemList=itemService.getItemsList(id);
    	ModelAndView mv=new ModelAndView(fname);
    	mv.addObject("slot", slot);
    	mv.addObject("itemList",itemList);
    	mv.addObject("gymBookRecord",book);
    	if(userType.equalsIgnoreCase("Admin")) {
    		List<String> userList=userService.getAllCustomers();
    		mv.addObject("userList",userList);
    	}
//    	itemList.forEach(item->System.out.println(item));
    	return mv;
    }
    @PostMapping("/slot-book")
    public ModelAndView saveSlotBookPage(
        @RequestParam("slot_id") Long slotId,
        @RequestParam("selectItem") Long itemId,
        @RequestParam("username") String username,
        @ModelAttribute("gymBookRecord") GymBook gymBook) {

        String userType = userService.getType();
        String bookingUserName = "";

        if ("Admin".equalsIgnoreCase(userType)) {
            bookingUserName = username;
        } else if ("Customer".equalsIgnoreCase(userType)) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            bookingUserName = authentication.getName();
        }

        // Check if the user has already booked a slot at the same time
        boolean isSlotAlreadyBooked = gymBookDao.existsBySlotIdAndUsername(slotId, bookingUserName);
        if (isSlotAlreadyBooked) {
            throw new SlotAlreadyBookedException("You have already booked a slot at this time.");
        }

        GymItem gymItem = gymItemDao.findItemById(itemId);
        SlotItemEmbed embed = new SlotItemEmbed(slotId, itemId);
        int totalSeat = gymItem.getTotalSeat();
        SlotItem slotItem = slotItemDao.findById(embed);
        int seatBooked = slotItemDao.findSeatBookedById(embed);
        int available = totalSeat - seatBooked;

        gymBook.setSlotId(slotId);
        gymBook.setItemId(itemId);
        gymBook.setUsername(bookingUserName);
        gymBookDao.save(gymBook);

        if (available > 0) {
            seatBooked++;
            slotItem.setSeatBooked(seatBooked);
            slotItemDao.save(slotItem);
        } else {
            throw new SeatNotAvailableException();
        }
        Long bookingId = gymBook.getBookingId();
        return new ModelAndView("redirect:/booking-card/" + bookingId);
    }

    @ExceptionHandler(SeatNotAvailableException.class)
    public ModelAndView handleSeatNotAvailableException(SeatNotAvailableException exception) {
        ModelAndView mv = new ModelAndView("exceptionPage");
        mv.addObject("errorMessage", exception.getMessage());
        return mv;
    }
    @ExceptionHandler(SlotAlreadyBookedException.class)
    public ModelAndView handleSlotAlreadyBookedException(SlotAlreadyBookedException exception) {
        ModelAndView mv = new ModelAndView("exceptionPage");
        mv.addObject("errorMessage", exception.getMessage());
        return mv;
    }

    @GetMapping("/booking-card/{id}")
    public ModelAndView showBookingCardPage(@PathVariable Long id) {
        GymBook booking = gymBookDao.findBookInfoById(id);
        ModelAndView mv = new ModelAndView("bookingCardPage");
        mv.addObject("booking", booking);
        return mv;
    }
    
    @GetMapping("/cancel-bookings")
    public ModelAndView cancelBookings() {
    	String username="";
    	String userType=userService.getType();
    	
    	List<GymBook> bookingList = gymBookDao.getBookList();
    	if(userType.equalsIgnoreCase("Customer")) {
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            username = authentication.getName();
            bookingList=gymBookDao.getEntitiesByUsername(username);
    	}
    //    System.out.println("Number of bookings fetched: " + bookingList.size());
        ModelAndView mv = new ModelAndView("gymBookCancelPage");
        mv.addObject("bookingList", bookingList);
        return mv;
    }
    @GetMapping("/bookings")
    public ModelAndView showAllBookings() {
    	String username="";
    	String userType=userService.getType();
    	
    	List<GymBook> bookingList = gymBookDao.getBookList();
    	if(userType.equalsIgnoreCase("Customer")) {
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            username = authentication.getName();
            bookingList=gymBookDao.getEntitiesByUsername(username);
    	}
    //    System.out.println("Number of bookings fetched: " + bookingList.size());
        ModelAndView mv = new ModelAndView("gymBookReportPage");
        mv.addObject("bookingList", bookingList);
        return mv;
    }
//    @PostMapping("/booked")
//    public ModelAndView cancelBooking(@RequestParam("slot_id") Long slotId,
//                                      @RequestParam("item_id") Long itemId,
//                                      @RequestParam("selectBookingId") Long bookingId) {
//        SlotItemEmbed embed = new SlotItemEmbed(slotId, itemId);
//        SlotItem slotItem = slotItemDao.findById(embed);
//        if (slotItem != null) {
//            int seatBooked = slotItem.getSeatBooked();
//            if (seatBooked > 0) {
//                seatBooked--;
//                slotItem.setSeatBooked(seatBooked);
//                slotItemDao.save(slotItem);
//                gymBookDao.deleteById(bookingId);
//            }
//        }
//        ModelAndView mv = new ModelAndView("redirect:/index");
//        return mv;
//    }
    @PostMapping("/booked")
    public ModelAndView cancelBooking(@RequestParam("selectBookingId") Long bookingId) {
        // Fetch the booking information using the booking ID
        GymBook gymBook = gymBookDao.findBookInfoById(bookingId);
        
        if (gymBook != null) {
            // Create an embedded ID for the SlotItem
            SlotItemEmbed embed = new SlotItemEmbed(gymBook.getSlotId(), gymBook.getItemId());
            
            // Find the SlotItem by the embedded ID
            SlotItem slotItem = slotItemDao.findById(embed);
            
            if (slotItem != null) {
                // Get the number of seats booked for the SlotItem
                int seatBooked = slotItem.getSeatBooked();
                
                if (seatBooked > 0) {
                    // Decrement the seat count
                    seatBooked--;
                    slotItem.setSeatBooked(seatBooked);
                    
                    // Save the updated SlotItem
                    slotItemDao.save(slotItem);
                    
                    // Delete the booking by ID
                    gymBookDao.deleteById(bookingId);
                    
                    // Log the successful cancellation
                    //System.out.println("Booking cancelled successfully. Booking ID: " + bookingId);
                } else {
                    // Log the case where there are no seats to cancel
                   // System.out.println("No seats to cancel for booking ID: " + bookingId);
                }
            } else {
                // Log the case where SlotItem is not found
                //System.out.println("SlotItem not found for slot ID: " + gymBook.getSlotId() + " and item ID: " + gymBook.getItemId());
            }
        } else {
            // Log the case where GymBook is not found
            //System.out.println("Booking not found for booking ID: " + bookingId);
        }

        // Redirect to the index page after cancellation
        return new ModelAndView("redirect:/index");
    }


    @GetMapping("/slot-item-add/{id}")
    public ModelAndView saveItemSlots(@PathVariable Long id) {
        itemService.addNewItemToSlots(id);   
        return new ModelAndView("redirect:/index");
    }
}
