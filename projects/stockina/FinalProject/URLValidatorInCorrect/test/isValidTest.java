import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class isValidTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


@Test
public void testIsValid() {
	//test UrlValidator()
		System.out.println("TESTING UrlValidator(null)");
        UrlValidator validator = new UrlValidator();
        
        //assert that all default good URLs pass
        for(int i=0; i<defaultGoods.length; i++) {
        System.out.println("testing " + defaultGoods[i]);
        assertTrue(validator.isValid(defaultGoods[i]));	
        }
        
        
        //assert that all default bad URLs test False
        for(int i=0; i<defaultBads.length; i++) {
        System.out.println("testing " + defaultBads[i]);
        assertFalse(validator.isValid(defaultBads[i]));	
        }
        System.out.println("done");
        System.out.println("");


        
        
    //test UrlValidator(null)
        System.out.println("TESTING UrlValidator(null)");

        UrlValidator validator2 = new UrlValidator(null);
        
        //assert that all default good URLs pass
        for(int i=0; i<defaultGoods.length; i++) {
        System.out.println("testing " + defaultGoods[i]);
        assertTrue(validator2.isValid(defaultGoods[i]));	
        }

        
        //assert that all default bad URLs test False
        for(int i=0; i<defaultBads.length; i++) {
        System.out.println("testing " + defaultBads[i]);
        assertFalse(validator2.isValid(defaultBads[i]));	
        }        
        System.out.println("done");
        System.out.println("");
        
        
        
     //test UrlValidator(String[] options)
        System.out.println("TESTING UrlValidator(String[] options)");
        String[] options = {"https", "http", "ftp", "funky"};
        
        UrlValidator validator3 = new UrlValidator(options);
        //assert that all default good URLs pass
        for(int i=0; i<defaultGoods.length; i++) {
        System.out.println("testing " + defaultGoods[i]);
        assertTrue(validator3.isValid(defaultGoods[i]));	
        }
        //assert that all bad URLs are False
        for(int i=0; i<defaultBads.length; i++) {
        System.out.println("testing " + defaultBads[i]);
        assertFalse(validator3.isValid(defaultBads[i]));	
        }   
        
        //assert that defined scheme is good
        for(int i=0; i<schemeOptionGood.length; i++) {
        System.out.println("testing " + schemeOptionGood[i]);
        assertTrue(validator3.isValid(schemeOptionGood[i]));	
        }
        
        //assert that scheme not in options is false
        for(int i=0; i<schemeOptionBad.length; i++) {
        System.out.println("testing " + schemeOptionBad[i]);
        assertFalse(validator3.isValid(schemeOptionBad[i]));	
        }
        assertTrue(validator3.isValid("http://somewhere.com/pathxyz/file(1).html"));
        
        
    //test isValid(long options)
        UrlValidator validator4 = new UrlValidator(UrlValidator.ALLOW_2_SLASHES);
        assertTrue(validator4.isValid("http://somewhere.com/pathxyz//file(1).html"));
       
        for(int i=0; i<defaultBads.length-1; i++) { //this leaves out the last case with 2slashes
        	System.out.println("options testing " + defaultBads[i]);
        	assertFalse(validator4.isValid(defaultBads[i]));	
        	}
        for(int i=0; i<defaultGoods.length; i++) {
        	System.out.println("testing " + defaultGoods[i]);
        	assertTrue(validator4.isValid(defaultGoods[i]));	
        }
}


String[] defaultGoods = {
//testing schemes
"http://www.google.org",
"http://www.facebook.com",
"ftp://www.facebook.com",
"https://www.facebook.com",

//testing authority
"http://www.facebook.com.",
"http://go.com",
"http://go.au",
"http://0.0.0.0",
"http://255.255.255.255",
"http://255.com",
"http://go.cc",


//testing ports
"http://www.google.org:80",
"http://www.google.org:65535",
"http://www.google.org:0",

//test path
"http://www.google.org:80/test1",
"http://www.google.org:65535/t$23",
"http://www.google.org:0/t123",
"http://www.google.org/test1/",
"http://www.google.org/test1/file",	

//test query
"http://www.google.org:80/test1?action=view",
"http://www.google.org:80/test1?action=edit&mode=up",
"http://www.google.org/test1?action=view",
"ftp://google.org?action=edit&mode=up",
};	

String[] testGoodScheme = {
	"http://www.facebook.com",
	"ftp://www.facebook.com",
	"https://www.facebook.com"
};

String[] testGoodAuthority = {
	"http://www.facebook.com.",
	"http://go.com",
	"http://go.au",
	"http://0.0.0.0",
	"http://255.255.255.255",
	"http://255.com",
	"http://go.cc"	
};

String[] testGoodPort = {
	"http://www.google.org:80",
	"http://www.google.org:65535",
	"http://www.google.org:0"
};

String[] testGoodPath = {
	"ftp://www.google.org:80/test1",
	"http://www.google.org:65535/t$23",
	"http://www.google.org:0/t123",
	"http://www.google.org/test1/",
	"http://www.google.org/test1/file"
};

String[] testGoodQuery = {
	"http://www.google.org:80/test1?action=view",
	"http://www.google.org:80/test1?action=edit&mode=up",
	"http://www.google.org/test1?action=view",
	"ftp://google.org?action=edit&mode=up"
};

String[] schemeOptionGood = {
		"funky://www.google.com",
		"funky://go.com",
		"funky://www.google.org:80",
		"funky://www.google.org:0/t123",
		"funky://www.google.org/test1/",
		"funky://www.google.org:80/test1?action=view",
};









String[] defaultBads = {
		//bad scheme
		"3ht://www.google.com",
		"http:/www.google.com",
		"http:www.google.com",
		"http/www.google.com",
		"://www.google.com",
		
		//bad authority
		"http://256.256.256.256",
		"http://1.2.3.4.",
		"http://1.2.3.4.5",
		"http://1.2.3",
		"http://.1.2.3.4",
		"http://go.a",
		"http://go.a1a",
		"http://go.1aa",
		"http://aaa.",
		"http://aaa",
		"http://",
		
		//bad port
		"http://www.google.com:65536",
		"http:/www.google.com:-1",
		"http:/www.google.com:65636",
		"http:/www.google.com:999999999999999999",
		"http:/www.google.com:65a",
		
		//bad path
		"http://www.google.com:80/..",
		"http://www.google.com/../",
		"http://www.google.com/..//file",
		"http://www.google.com/test1//file"
};


String[] testDefaultBadScheme = {
		"3ht://www.google.com",
		"http:/www.google.com",
		"http:www.google.com",
		"http/www.google.com",
		"://www.google.com"
};


String[] testDefaultBadAuthority = {
		"http://256.256.256.256",
		"http://1.2.3.4.",
		"http://1.2.3.4.5",
		"http://1.2.3",
		"http://.1.2.3.4",
		"http://go.a",
		"http://go.a1a",
		"http://go.1aa",
		"http://aaa.",
		"http://aaa",
		"http://"
};

String[] testDefaultBadPort = {
		"http://www.google.com:65536",
		"http:/www.google.com:-1",
		"http:/www.google.com:65636",
		"http:/www.google.com:999999999999999999",
		"http:/www.google.com:65a"
};
		
String[] testDefaultBadPath = {
		"http://www.google.com:80/..",
		"http://www.google.com/../",
		"http://www.google.com/..//file",
		"http://www.google.com/test1//file"
};

String[] schemeOptionBad = {
		"file://www.google.com",
		"h3t://go.com",
		"bad://www.google.org:80",
		"unfunky://www.google.org:0/t123",
		"test://www.google.org/test1/",
		"smtp://www.google.org:80/test1?action=view",
};




}
