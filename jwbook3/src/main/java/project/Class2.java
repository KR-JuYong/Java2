package project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Class2 {
	private WebDriver driver;
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "D:/workspace/java/base/lib/chromedriver.exe";
	public String URL = "https://www.10000recipe.com/recipe/list.html?q=";

	public Class2() {
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		ChromeOptions options = new ChromeOptions();
		// options.addArguments("headless");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
	}

	public static void main(String[] args) throws Exception {
		Class2 cs = new Class2();
		List<String> resultList = cs.getSearchCook();
		cs.getCookInfo(resultList);
	}

	public List<String> getSearchCook() throws IOException {
		List<String> cookList = new ArrayList<>();
		try {
			String newUrl = URL + "돼지고기";
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

	public void getCookInfo(List<String> resultList) {
		try {
			for (String code : resultList) {
				String param = "/";
				param += param + code;
				URL = "https://www.10000recipe.com/recipe";
				String newUrl = URL + param;
				driver.get(newUrl);

				String title = driver.findElement(By.xpath("//*[@id=\"contents_area\"]/div[2]/h3")).getText();
				System.out.println("레시피 정보: " + title + ", <" + newUrl + ">");
				}
		}catch (Exception e) {
			e.printStackTrace();
			driver.close();
		}
		driver.close();
	}
}
