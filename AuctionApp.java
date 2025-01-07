import java.util.*;
// class diagram 
// Main Class
public class AuctionApp {
    public static void main(String[] args) {
        // Create Users
        User buyer1 = new User("U1", "Alice", "alice@example.com", "Bidder");
        User buyer2 = new User("U2", "Bob", "bob@example.com", "Bidder");

        // Create Auction
        Auction auction = new Auction("A1", "Laptop", 500.0, new Date(), new Date(System.currentTimeMillis() + 3600000), "Active");

        // Place Bids
        System.out.println(buyer1.getName() + " is placing a bid of 600.0.");
        auction.addBid(new Bid("B1", auction.getAuctionID(), buyer1.getUserID(), 600.0, new Date()));

        System.out.println(buyer2.getName() + " is placing a bid of 700.0.");
        auction.addBid(new Bid("B2", auction.getAuctionID(), buyer2.getUserID(), 700.0, new Date()));

        // Determine Winner
        Bid highestBid = auction.getHighestBid();
        System.out.println("The highest bid is " + highestBid.getBidAmount() + " by User ID: " + highestBid.getUserID());

        // Process Payment
        Payment payment = new Payment("P1", auction.getAuctionID(), highestBid.getUserID(), highestBid.getBidAmount(), "Completed");
        System.out.println("Payment Status: " + payment.getPaymentStatus());

        // Send Notification
        NotificationService notification = new NotificationService("N1", highestBid.getUserID(), "Congratulations! You have won the auction and your payment is completed.", new Date());
        notification.sendNotification();

        System.out.println("Auction completed successfully.");
    }
}

// User.java
class User {
    private String userID;
    private String name;
    private String email;
    private String role; // Bidder, Seller

    public User(String userID, String name, String email, String role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }
}

// Auction.java
class Auction {
    private String auctionID;
    private String item;
    private double startingPrice;
    private double currentBid;
    private Date startTime;
    private Date endTime;
    private String status; // Active, Ended, Canceled
    private List<Bid> bids;

    public Auction(String auctionID, String item, double startingPrice, Date startTime, Date endTime, String status) {
        this.auctionID = auctionID;
        this.item = item;
        this.startingPrice = startingPrice;
        this.currentBid = startingPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.bids = new ArrayList<>();
    }

    public boolean addBid(Bid bid) {
        if (bid.getBidAmount() > currentBid) {
            currentBid = bid.getBidAmount();
            bids.add(bid);
            return true;
        }
        return false;
    }

    public Bid getHighestBid() {
        return bids.stream().max(Comparator.comparingDouble(Bid::getBidAmount)).orElse(null);
    }

    public String getAuctionID() {
        return auctionID;
    }
}

// Bid.java
class Bid {
    private String bidID;
    private String auctionID;
    private String userID;
    private double bidAmount;
    private Date timestamp;

    public Bid(String bidID, String auctionID, String userID, double bidAmount, Date timestamp) {
        this.bidID = bidID;
        this.auctionID = auctionID;
        this.userID = userID;
        this.bidAmount = bidAmount;
        this.timestamp = timestamp;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public String getUserID() {
        return userID;
    }
}

// Payment.java
class Payment {
    private String paymentID;
    private String auctionID;
    private String userID;
    private double amount;
    private String paymentStatus; // Pending, Completed, Failed

    public Payment(String paymentID, String auctionID, String userID, double amount, String paymentStatus) {
        this.paymentID = paymentID;
        this.auctionID = auctionID;
        this.userID = userID;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
}

// NotificationService.java
class NotificationService {
    private String notificationID;
    private String userID;
    private String message;
    private Date timestamp;

    public NotificationService(String notificationID, String userID, String message, Date timestamp) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.message = message;
        this.timestamp = timestamp;
    }

    public void sendNotification() {
        System.out.println("Notification to User ID: " + userID + " - " + message);
    }
}
