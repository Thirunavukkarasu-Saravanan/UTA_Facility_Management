package com.group4.macfms.selenium.functions;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SnapshotFunction extends SeleniumFunctionsBase {

	   public void takeScreenshot(String screenshotname) {
		   try
			  {
				  File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);			
				  FileUtils.copyFile(source, new File("./screenShots/" + screenshotname +".png"));
			  }
			  catch(IOException e) {}
			  try {
//				  Change the delay value to 1_000 to insert a 1 second delay after 
//				  each screenshot
				  Thread.sleep(1_000);
			} catch (InterruptedException e) {}
	   }
}