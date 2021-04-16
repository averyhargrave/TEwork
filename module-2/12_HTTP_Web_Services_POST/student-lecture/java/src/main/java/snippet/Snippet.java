package snippet;

public class Snippet {
	  HttpHeaders theHeaders = new HttpHeaders();
	        theHeaders.setContentType(MediaType.APPLICATION_JSON);
	        
	        HttpEntity theEntity = new HttpEntity(anAuction, theHeaders);
}

