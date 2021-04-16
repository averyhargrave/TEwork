package com.techelevator.auctions.controller;

import com.techelevator.auctions.DAO.AuctionDAO;
import com.techelevator.auctions.DAO.MemoryAuctionDAO;
import com.techelevator.auctions.model.Auction;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private AuctionDAO dao;

    public AuctionController() {
        this.dao = new MemoryAuctionDAO();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(required=false,defaultValue="") String title_like
    						 ,@RequestParam(required=false,defaultValue="0") double currentBid_lte) {
    	
    	if(!title_like.equals("") && (currentBid_lte > 0)) {
  		   return dao.searchByTitleAndPrice(title_like, currentBid_lte);
    	}
    	else if(!title_like.equals("")) {
    		return dao.searchByTitle(title_like);
    	}
    	else if(currentBid_lte > 0) {
    		return dao.searchByPrice(currentBid_lte);
    	}
    	return dao.list();
    }
    
    @RequestMapping(path = "/{id}") // if you don't have the method, we assume GET
    public Auction getAuction(@PathVariable int id) {
    	System.out.println("Called with the path /auctions/" + id);
        return dao.get(id);
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public Auction createAuction(@RequestBody Auction anAuction) { 
    	System.out.println("Called with the path /auctions");
    	return dao.create(anAuction); 	
    }  
}
