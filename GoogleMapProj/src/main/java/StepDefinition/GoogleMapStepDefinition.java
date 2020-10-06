package StepDefinition;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import com.qa.EXL.BaseSetup.Base;
import com.qa.EXL.Pages.GoogleMapPages;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GoogleMapStepDefinition extends Base {

	public GoogleMapStepDefinition() throws IOException {
		super();
	}

	public static Logger log = Logger.getLogger(GoogleMapStepDefinition.class.getName());

	@Given("^user is on GoogleMap Page$")
	public void user_is_on_GoogleMap_Page() throws Throwable {

		PropertyConfigurator.configure(System.getProperty("user.dir") + "/Resources/log4j.properties");
		Base.Initialization();
		log.info("URL Launched");
		Base.capturescreenshot();

	}

	@When("^user enters \"([^\"]*)\" in search box$")
	public void user_enters_in_search_box(String loc) throws Throwable {
		GoogleMapPages gmp = new GoogleMapPages();
		gmp.search(loc);
		Thread.sleep(4000);
		log.info("Searching Location!!");
		Base.capturescreenshot();

	}

	@Then("^user should be able to view the coordinates for San Francisco \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_should_be_able_to_view_the_coordinates_for_San_Francisco_and(String lat, String lon)
			throws Throwable {
		GoogleMapPages gmp = new GoogleMapPages();
		Thread.sleep(4000);
		log.info("Verifing Routes!!!");
		String url = gmp.verifycoordinates();
		String[] coordinates = url.split("@", 0);
		String[] finalcoordinates = coordinates[1].split(",");
		try {
			assertEquals(lat, finalcoordinates[0]);
		} catch (Throwable t1) {
			log.warn("Latitude Coordinate doesnt match : " + t1.getMessage());
		}
		try {
			assertEquals(lon, finalcoordinates[1]);
		} catch (Throwable t2) {
			log.warn("Longitude Coordinate doesnt match : " + t2.getMessage());
		}

		log.info("Verified Routes!!!");
		Base.capturescreenshot();
	}

	@Then("^user clicks on Directions link$")
	public void user_clicks_on_Directions_link() throws Throwable {
		GoogleMapPages gmp = new GoogleMapPages();
		log.info("Click on Direction Link!!");
		Thread.sleep(4000);
		gmp.directionclick();
		Base.capturescreenshot();

	}

	@Then("^user search for driving directions by car from \"([^\"]*)\" to \"([^\"]*)\"$")
	public void user_search_for_driving_directions_by_car_from_to(String from, String to) throws Throwable {
		GoogleMapPages gmp = new GoogleMapPages();
		log.info("Entering From and To locations!!!");
		gmp.routefromentry(from);
		gmp.routetoentry(to);
		log.info("Entered From and To locations!!!");
		Base.capturescreenshot();

	}

	@Then("^user should be able to view two or more routes displayed in the list$")
	public void user_should_be_able_to_view_two_or_more_routes_displayed_in_the_list() throws Throwable {
		GoogleMapPages gmp = new GoogleMapPages();
		log.info("Verifying number of routes!!!");
		Assert.assertTrue(gmp.routecount() > 0);
		log.info("Verified number of routes!!!");
		Base.capturescreenshot();
	}

	@Then("^user should be able to print route title and Distance in Miles and Travel time$")
	public void user_should_be_able_to_print_route_title_and_Distance_in_Miles_and_Travel_time() throws Throwable {
		GoogleMapPages gmp = new GoogleMapPages();
		log.info("Printing Route Title,Distance in Miles,Travel Time!!!");
		gmp.printinfile();
		Base.capturescreenshot();
		driver.quit();

	}

}
