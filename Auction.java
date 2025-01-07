import java.util.*;

// Enum for User Roles
enum UserRole {
    SELLER,
    BUYER
}

// Auction Class with Bid Logic
public class Auction {
    private String auctionID;
    private double highestBid;
    private String highestBidder;
    private List<Bid> bids;
    private String item;
    private Date startTime;
    private Date endTime;

    public Auction(String auctionID, String item) {
        this.auctionID = auctionID;
        this.bids = new ArrayList<>();
        this.item = item;
        this.highestBid = 0.0;
        this.highestBidder = null;
    }

    // Create Auction
    public void createAuction(String itemDetails) {
        System.out.println("Auction created with item: " + itemDetails);
    }

    // Start Auction
    public void startAuction(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        System.out.println("Auction for " + item + " scheduled from " + startTime + " to " + endTime);
    }

    // Place a Bid
    public void placeBid(String bidder, double bidAmount) {
        if (bidAmount > highestBid) {
            highestBid = bidAmount;
            highestBidder = bidder;
            bids.add(new Bid(bidder, bidAmount));
            System.out.println("Bid placed by " + bidder + " for amount: " + bidAmount);
        } else {
            System.out.println("Bid amount is lower than current highest bid.");
        }
    }

    // End Auction and Determine Winner
    public void endAuction() {
        System.out.println("Auction for " + item + " has ended.");
        System.out.println("Winner: " + highestBidder + " with bid amount: " + highestBid);
        notifyWinner(highestBidder);
    }

    // Process Payment
    public void processPayment(boolean isSuccess) {
        if (isSuccess) {
            System.out.println("Payment processing successful.");
            notifyPaymentSuccess(highestBidder);
        } else {
            System.out.println("Payment failed.");
        }
    }

    // Notify Winner of Auction
    private void notifyWinner(String winner) {
        System.out.println("Notification: Congratulations " + winner + ", you have won the auction!");
    }

    // Notify Payment Success
    private void notifyPaymentSuccess(String buyer) {
        System.out.println("Notification: Payment successfully processed for " + buyer);
    }

    public static void main(String[] args) {
        Auction auction = new Auction("A1", "Laptop");

        // Seller creates the auction
        auction.createAuction("Laptop");

        // Auction is scheduled
        auction.startAuction(new Date(), new Date(System.currentTimeMillis() + 3600000));

        // Buyers place bids
        auction.placeBid("Buyer1", 500.0);
        auction.placeBid("Buyer2", 600.0);
        auction.placeBid("Buyer3", 700.0);

        // Auction ends
        auction.endAuction();

        // Process payment (successful)
        auction.processPayment(true);
    }

    // Bid class to represent each bid
    static class Bid {
        private String bidder;
        private double amount;

        public Bid(String bidder, double amount) {
            this.bidder = bidder;
            this.amount = amount;
        }

        public String getBidder() {
            return bidder;
        }

        public double getAmount() {
            return amount;
        }
    }
}
