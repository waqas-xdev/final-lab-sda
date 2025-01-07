import java.util.*;

// Enum for Auction States
enum AuctionState {
    USER_SESSION_ACTIVE,
    AUCTION_CREATED,
    AUCTION_SCHEDULED,
    AUCTION_ACTIVE,
    AUCTION_CANCELED,
    AUCTION_ENDED,
    PAYMENT_PROCESSING,
    PAYMENT_FAILED,
    AUCTION_COMPLETE,
    AUCTION_REOPENED
}

// Auction Class with State Management
public class Auction {
    private String auctionID;
    private AuctionState state;

    public Auction(String auctionID) {
        this.auctionID = auctionID;
        this.state = AuctionState.USER_SESSION_ACTIVE;
    }

    public void createAuction(String itemDetails, double startingPrice) {
        if (state == AuctionState.USER_SESSION_ACTIVE) {
            System.out.println("Auction created with details: " + itemDetails + ", Starting Price: " + startingPrice);
            state = AuctionState.AUCTION_CREATED;
        } else {
            System.out.println("Invalid state transition.");
        }
    }

    public void scheduleAuction(Date startTime, Date endTime) {
        if (state == AuctionState.AUCTION_CREATED) {
            System.out.println("Auction scheduled from " + startTime + " to " + endTime);
            state = AuctionState.AUCTION_SCHEDULED;
        } else {
            System.out.println("Invalid state transition.");
        }
    }

    public void startAuction() {
        if (state == AuctionState.AUCTION_SCHEDULED) {
            System.out.println("Auction is now active.");
            state = AuctionState.AUCTION_ACTIVE;
        } else {
            System.out.println("Invalid state transition.");
        }
    }

    public void cancelAuction() {
        if (state == AuctionState.AUCTION_CREATED || state == AuctionState.AUCTION_SCHEDULED || state == AuctionState.AUCTION_ACTIVE) {
            System.out.println("Auction has been canceled.");
            state = AuctionState.AUCTION_CANCELED;
        } else {
            System.out.println("Invalid state transition.");
        }
    }

    public void endAuction() {
        if (state == AuctionState.AUCTION_ACTIVE) {
            System.out.println("Auction has ended.");
            state = AuctionState.AUCTION_ENDED;
        } else {
            System.out.println("Invalid state transition.");
        }
    }

    public void processPayment(boolean isSuccess) {
        if (state == AuctionState.AUCTION_ENDED) {
            if (isSuccess) {
                System.out.println("Payment processing successful.");
                state = AuctionState.AUCTION_COMPLETE;
            } else {
                System.out.println("Payment failed.");
                state = AuctionState.PAYMENT_FAILED;
            }
        } else {
            System.out.println("Invalid state transition.");
        }
    }

    public void reopenAuction() {
        if (state == AuctionState.PAYMENT_FAILED) {
            System.out.println("Auction reopened for new bids.");
            state = AuctionState.AUCTION_REOPENED;
        } else {
            System.out.println("Invalid state transition.");
        }
    }

    public AuctionState getState() {
        return state;
    }

    public static void main(String[] args) {
        Auction auction = new Auction("A1");

        // State Transitions
        auction.createAuction("Laptop", 500.0);
        auction.scheduleAuction(new Date(), new Date(System.currentTimeMillis() + 3600000));
        auction.startAuction();
        auction.endAuction();
        auction.processPayment(true);

        System.out.println("Final State: " + auction.getState());
    }
}

