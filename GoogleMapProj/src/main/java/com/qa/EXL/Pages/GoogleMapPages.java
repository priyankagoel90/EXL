package com.qa.EXL.Pages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.EXL.BaseSetup.Base;

public class GoogleMapPages extends Base {

	public GoogleMapPages() throws IOException {
		PageFactory.initElements(driver, this);
	}

	int i = 0;

	@FindBy(id = "searchboxinput")
	WebElement searchbox;

	@FindBy(id = "searchbox-searchbutton")
	WebElement searchbutton;

	@FindBy(xpath = "//*[@id=\"pane\"]/div/div[1]/div/div/div[5]/div[1]/div/button")
	WebElement direction;

	@FindBy(xpath = "//*[@id=\"omnibox-directions\"]/div/div[2]/div/div/div[1]/div[2]/button")
	WebElement bycar;

	@FindBy(xpath = "//*[@id=\"sb_ifc51\"]/input")
	WebElement from;

	@FindBy(xpath = "//*[@id=\"sb_ifc52\"]/input")
	WebElement to;

	@FindBy(xpath = "//div[@class='section-directions-trip-description']")
	List<WebElement> directioncount;

	@FindBy(xpath = "//*[@id=\\\"section-directions-trip-\"+i+\"\\\"]/div/div/div/div")
	WebElement traveltime;

	@FindBy(xpath = "//*[@id=\\\"section-directions-trip-title-\"+i+\"\\\"]")
	WebElement route;

	@FindBy(xpath = "//*[@id=\\\"section-directions-trip-\"+i+\"\\\"]/div[1]/div[1]/div[1]/div[2]/div")
	WebElement miles;

	public void search(String sen) {
		searchbox.sendKeys(sen);
		searchbutton.click();
	}

	public String verifycoordinates() {
		return driver.getCurrentUrl();
	}

	public void directionclick() {
		direction.click();
	}

	public void routefromentry(String f) {
		bycar.click();
		from.sendKeys(f);
	}

	public void routetoentry(String t) {
		to.clear();
		to.sendKeys(t);
		to.sendKeys(Keys.ENTER);
	}

	public int routecount() {
		return directioncount.size();
	}

	public void printinfile() throws IOException {

		File file = new File("./TargetOutput/routes.txt");
		FileWriter fw = new FileWriter(file, false);
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			for (int i = 0; i < directioncount.size(); i++) {
				String traveltime = directioncount.get(i)
						.findElement(By.xpath("//*[@id=\"section-directions-trip-" + i + "\"]/div/div/div/div"))
						.getText();
				String route = directioncount.get(i)
						.findElement(By.xpath("//*[@id=\"section-directions-trip-title-" + i + "\"]")).getText();
				String miles = directioncount.get(i)
						.findElement(By.xpath(
								"//*[@id=\"section-directions-trip-" + i + "\"]/div[1]/div[1]/div[1]/div[2]/div"))
						.getText();
				filetext(route, miles, traveltime, bw);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			bw.flush();
			bw.close();
		}
	}

}
