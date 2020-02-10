import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class UpdateRequest {
	private String destination;
	private Account theAccount;
	private String requestStatus;
	private String fullUrl;

	public UpdateRequest(String destination, Account theAccount) {
		this.destination = destination;
		this.theAccount = theAccount;
		this.requestStatus = "The request has not been made yet.";
		this.fullUrl = String.format("%s/%s/%s/%s", 
				destination, 
				theAccount.getAccountName(), 
				theAccount.getLedgerBalance(), 
				theAccount.getAvailableBalance()
			); //destination/accountName/ledgerBalance/availableBalance
	}

    public void makeGetRequest() { //make the get request to the provided destination
    	HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.fullUrl))
                .build();

        try{
        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        	this.requestStatus = response.toString();
        } catch (Exception e) {
        	System.out.println("Connection error occured.");
        }
        
    }

    public String getRequestStatus() {
    	return this.requestStatus;
    }
}