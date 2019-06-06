import junit.framework.TestCase;
import java.util.Random;

public class UrlValidatorTestRandom extends TestCase{
	
	public UrlValidatorTestRandom(String testName) {
		      super(testName);
	}
	
	
	// split up result pairs into expected valid/invalid
    ResultPair[] urlSchemeValid = { 
    		new ResultPair("http://", true), 
    		new ResultPair("ftp://", true), 
            new ResultPair("https://", true)
    };
    
    ResultPair[] urlSchemeInvalid = {
			new ResultPair("3ht://", false), 
			new ResultPair("http:", false), 
			new ResultPair("://", false)
    };

    ResultPair[] urlAuthorityValid = { 
    		new ResultPair("www.google.com", true), 
    		new ResultPair("go.com", true),
    		new ResultPair("255.255.255.255", true),
    		new ResultPair("amazon.com", true),
    		new ResultPair("www.apache.org", true)
    };
    
    ResultPair[] urlAuthorityInvalid = { 
    		new ResultPair("1.2.3.4.5", false), 
    		new ResultPair("aaa", false),
            new ResultPair("go.a1a", false),
            new ResultPair("7654:3210:FEDC:BA98:7654:3210", false),
            new ResultPair("...", false)
    };

    ResultPair[] urlPortValid = { 
    		new ResultPair("", true), 
    		new ResultPair(":65535", true), 
    		new ResultPair(":80", true)
    };
    
    ResultPair[] urlPortInvalid = { 
    		new ResultPair(":-1", false), 
    		new ResultPair(":65a", false),
            new ResultPair(":65536", false)
    };

    ResultPair[] urlPathValid = { 
    		new ResultPair("/test1", true), 
    		new ResultPair("/t123", true), 
    		new ResultPair("", true)
    };
    
    ResultPair[] urlPathInvalid = { 
    		new ResultPair("/..", false), 
    		new ResultPair("/..//file", false),
            new ResultPair("/test1//file", false),
            new ResultPair(null, false)
    };

    ResultPair[] urlQueryValid = { 
    		new ResultPair("?action=view", true), 
    		new ResultPair("?action=edit&mode=up", true),
    		new ResultPair("", true) 
    };
    
    
    public void testisValidRandom() {
    	System.out.println("----------Start of Random Test-------------");
    	System.out.println("");
    	UrlValidator val = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
    	long time = System.currentTimeMillis();
    	Random random = new Random(time);
    	int failures = 0;
    	
    	// test URLs that are supposed to be valid
    	System.out.println("-----Checking Valid URLs-----");
    	for(int i = 0; i < 2000; i++) {
    		StringBuilder url = new StringBuilder();
    		
    		String scheme = urlSchemeValid[random.nextInt(urlSchemeValid.length)].item;
    		url.append(scheme);
    		
    		String authority = urlAuthorityValid[random.nextInt(urlAuthorityValid.length)].item;
    		url.append(authority);
    		
    		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
    		url.append(port);
    		
    		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
    		url.append(path);
    		
    		String query = urlQueryValid[random.nextInt(urlQueryValid.length)].item;
    		url.append(query);
    		
    		if(val.isValid(url.toString()) != true) {
    			System.out.println("Valid URL: " + url.toString() + " has failed.");
    			failures++;
    		}	
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished Valid URLs-----");
    	System.out.println("");
    	
    	
    	System.out.println("-----Checking Invalid Schemes-----");
    	failures = 0;
    	for(int i = 0; i < 2000; i++) {
    		StringBuilder url = new StringBuilder();
    		
    		String scheme = urlSchemeInvalid[random.nextInt(urlSchemeInvalid.length)].item;
    		url.append(scheme);
    		
    		String authority = urlAuthorityValid[random.nextInt(urlAuthorityValid.length)].item;
    		url.append(authority);
    		
    		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
    		url.append(port);
    		
    		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
    		url.append(path);
    		
    		String query = urlQueryValid[random.nextInt(urlQueryValid.length)].item;
    		url.append(query);
    		
    		if(val.isValid(url.toString()) == true) {
    			System.out.println("Invalid URL: " + url.toString() + " has failed.");
    			failures++;
    		}
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished Invalid Schemes-----");
    	System.out.println("");
    	
    	
    	System.out.println("-----Checking Invalid Authorities-----");
    	failures = 0;
    	for(int i = 0; i < 2000; i++) {
    		StringBuilder url = new StringBuilder();
    		
    		String scheme = urlSchemeValid[random.nextInt(urlSchemeValid.length)].item;
    		url.append(scheme);
    		
    		String authority = urlAuthorityInvalid[random.nextInt(urlAuthorityInvalid.length)].item;
    		url.append(authority);
    		
    		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
    		url.append(port);
    		
    		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
    		url.append(path);
    		
    		String query = urlQueryValid[random.nextInt(urlQueryValid.length)].item;
    		url.append(query);
    		
    		if(val.isValid(url.toString()) == true) {
    			System.out.println("Invalid URL: " + url.toString() + " has failed.");
    			failures++;
    		}
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished Invalid Authorities-----");
    	System.out.println("");
    	
    	
    	System.out.println("-----Checking Invalid Ports-----");
    	failures = 0;
    	for(int i = 0; i < 2000; i++) {
    		StringBuilder url = new StringBuilder();
    		
    		String scheme = urlSchemeValid[random.nextInt(urlSchemeValid.length)].item;
    		url.append(scheme);
    		
    		String authority = urlAuthorityValid[random.nextInt(urlAuthorityValid.length)].item;
    		url.append(authority);
    		
    		String port = urlPortInvalid[random.nextInt(urlPortInvalid.length)].item;
    		url.append(port);
    		
    		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
    		url.append(path);
    		
    		String query = urlQueryValid[random.nextInt(urlQueryValid.length)].item;
    		url.append(query);
    		
    		if(val.isValid(url.toString()) == true) {
    			System.out.println("Invalid URL: " + url.toString() + " has failed.");
    			failures++;
    		}
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished Invalid Ports-----");
    	System.out.println("");
    	
    	
    	System.out.println("-----Checking Invalid Paths-----");
    	failures = 0;
    	for(int i = 0; i < 2000; i++) {
    		StringBuilder url = new StringBuilder();
    		
    		String scheme = urlSchemeValid[random.nextInt(urlSchemeValid.length)].item;
    		url.append(scheme);
    		
    		String authority = urlAuthorityValid[random.nextInt(urlAuthorityValid.length)].item;
    		url.append(authority);
    		
    		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
    		url.append(port);
    		
    		String path = urlPathInvalid[random.nextInt(urlPathInvalid.length)].item;
    		url.append(path);
    		
    		String query = urlQueryValid[random.nextInt(urlQueryValid.length)].item;
    		url.append(query);
    		
    		if(val.isValid(url.toString()) == true) {
    			System.out.println("Invalid URL: " + url.toString() + " has failed.");
    			failures++;
    		}
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished Invalid Paths-----");
    	System.out.println("");
    	
    	
    	System.out.println("-----Checking Invalid Queries-----");
    	for(int i = 0; i < 2000; i++) {
    		StringBuilder url = new StringBuilder();
    		
    		String scheme = urlSchemeValid[random.nextInt(urlSchemeValid.length)].item;
    		url.append(scheme);
    		
    		String authority = urlAuthorityValid[random.nextInt(urlAuthorityValid.length)].item;
    		url.append(authority);
    		
    		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
    		url.append(port);
    		
    		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
    		url.append(path);
    		
    		String queries[] = { "? &", "&^&*%$#" };
    		String query = queries[random.nextInt(queries.length)];
    		url.append(query);
    		
    		if(val.isValid(url.toString()) == true) {
    			System.out.println("Valid URL: " + url.toString() + " has failed.");
    			failures++;
    		}	
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished Invalid Queries-----");
    	System.out.println("");
    	
    	
    	System.out.println("-----Checking Fragment URLs-----");
        String[] schemes = {"http","https"};
        UrlValidator fragVal = new UrlValidator(schemes, UrlValidator.NO_FRAGMENTS);
        failures = 0;
        
    	for(int i = 0; i < 2000; i++) {
    		StringBuilder fragment = new StringBuilder();
    		
    		String scheme = urlSchemeValid[random.nextInt(urlSchemeValid.length)].item;
    		fragment.append(scheme);
    		
    		String authority = urlAuthorityValid[random.nextInt(urlAuthorityValid.length)].item;
    		fragment.append(authority);
    		
    		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
    		fragment.append(port);
    		
    		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
    		fragment.append(path);
    		
    		String query = urlQueryValid[random.nextInt(urlQueryValid.length)].item;
    		fragment.append(query);
    		
	        String frag = "#fragment";
	        fragment.append(frag);
    	
			if(fragVal.isValid(fragment.toString()) == true) {
				System.out.println("Invalid URL: " + fragment.toString() + " has failed.");
				failures++;
			}
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished Fragment URLs-----");
    	System.out.println(""); 
    	
    	
    	System.out.println("-----Checking File Special Cases-----");
    	failures = 0;
		UrlValidator fileVal = new UrlValidator(new String[] { "file" }, UrlValidator.ALLOW_LOCAL_URLS);
    	for(int i = 0; i < 100; i++) {
    		StringBuilder url = new StringBuilder();
    		
    		String scheme = "file:";
    		url.append(scheme);
    		
    		String auths[] = { "contains:colon", ":::", null, "//C:", "///C:\\:" };
    		String authority = auths[random.nextInt(auths.length)];
    		url.append(authority);
    		
    		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
    		url.append(port);
    		
    		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
    		url.append(path);
    		
    		if(authority != null && fileVal.isValid(url.toString()) == true) {
    			System.out.println("Invalid URL: " + url.toString() + " has failed.");
    			failures++;
    		}
    	}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished File Special Cases-----");
    	System.out.println("");
    	
    	
    	
    	// try a null string url
    	System.out.println("-----Checking null URL-----");
    	String nullurl = null;
    	if(val.isValid(nullurl) == true) {
			System.out.println("Invalid URL: " + nullurl + " has failed.");
			failures++;	    	System.out.println("Failures: " + failures);
    	}
    	else {
        	System.out.println("Invalid null url check passed.");
    	}
    	System.out.println("-----Finished null URL-----");
    	System.out.println("");  	
    	
    	
    	
    	System.out.println("-----Checking invalid pattern URLs-----");
    	failures = 0;
		StringBuilder noturl = new StringBuilder();
		
		String path = urlPathValid[random.nextInt(urlPathValid.length)].item;
		noturl.append(path);
		
		String authority = urlAuthorityValid[random.nextInt(urlAuthorityValid.length)].item;
		noturl.append(authority);
		
		String scheme = urlSchemeValid[random.nextInt(urlSchemeValid.length)].item;
		noturl.append(scheme);
		
		String port = urlPortValid[random.nextInt(urlPortValid.length)].item;
		noturl.append(port);
		
		String notaurl = "http://www.éçЯ☂ ֍௵";
		
		if(val.isValid(noturl.toString()) == true) {
			System.out.println("Invalid URL: " + noturl.toString() + " has failed.");
			failures++;
		}
		if(val.isValid(notaurl) == true) {
			System.out.println("Invalid URL: " + noturl.toString() + " has failed.");
			failures++;
		}
    	System.out.println("Failures: " + failures);
    	System.out.println("-----Finished invalid pattern URLs-----");
  
    	
    	System.out.println("");
    	System.out.println("----------End of Random Test-------------");
    }
}