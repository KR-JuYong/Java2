package Market;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Recipe {
	private WebDriver driver;
	private static String url;
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "D:/workspace/java/base/lib/chromedriver.exe";
	public Recipe() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("headless");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	}

	public static void main(String[] args) throws Exception {
		Recipe recipe = new Recipe();
		
		List<String> resultList = recipe.getSearchCook(url);
		recipe.getCookInfo(resultList);
		
		
	}
	
	public List<String> resultList() throws Exception{
		Recipe recipe = new Recipe();
		List<String> resultList = recipe.getSearchCook(url);
		recipe.getCookInfo(resultList);
		
		return resultList;
	}
	
	public List<String> getSearchCook(String url) throws IOException {
		List<String> cookList = new ArrayList<>();
		try {
			
			String newUrl = url;
			driver.get(newUrl);
			for (int i = 0; i < 3; i++) {

				String data = driver
						.findElement(By.xpath("//*[@id=\"contents_area_full\"]/ul/ul/li[" + (i + 1) + "]/div[1]/a"))
						.getAttribute("href");
				int index = data.lastIndexOf("/");
				String code = data.substring(index + 1).trim();
				cookList.add(code);
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			driver.close();
		}
		return cookList;
	}

	public String getCookInfo(List<String> resultList) throws IOException{
		try {
			for (String code : resultList) {
				String param = "/";
				param += param + code;
				String URL = "https://www.10000recipe.com/recipe";
				String newUrl = URL + param;
				driver.get(newUrl);

				String title = driver.findElement(By.xpath("//*[@id=\"contents_area\"]/div[2]/h3")).getText();
				String recipe="<"+title+", "+ newUrl+">";
				return recipe;
				}
		}catch (Exception e) {
			e.printStackTrace();
			driver.close();
		}
		driver.close();
		return null;
	} 

}
