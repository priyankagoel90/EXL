package com.qa.EXL.BaseSetup;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public static WebDriver driver;
	public static Properties p;
	// public static Logger log = Logger.getLogger(Base.class.getName());

	public Base() throws IOException {
		p = new Properties();
		String path = System.getProperty("user.dir");
		// PropertyConfigurator.configure(System.getProperty("user.dir") +
		// "/Resources/log4j.properties");

		FileInputStream fis = new FileInputStream("./Resources/config.properties");
		p.load(fis);
	}

	public static void Initialization() {
		String browsername = p.getProperty("browser");

		if (browsername.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browsername.equals("Firefox")) {
			WebDriverManager.chromedriver().setup();
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(p.getProperty("Page_load_timeout")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(p.getProperty("Implicitly_wait")), TimeUnit.SECONDS);
		driver.get(p.getProperty("url"));
		// log.info("URL Launched");
	}

	public BufferedWriter filetext(String a, String b, String c, BufferedWriter bw) throws IOException {
		bw.write("Route : " + a);
		bw.newLine();
		bw.write("Miles  : " + b);
		bw.newLine();
		bw.write("Travel Time : " + c);
		bw.newLine();
		bw.newLine();
		return bw;
	}

	public static void capturescreenshot() throws IOException {
		Date d = new Date();
		String filename = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("./Screenshots/" + filename));
	}

}
