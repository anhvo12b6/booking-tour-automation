import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.configuration.RunConfiguration

// Step 1: Mở trình duyệt và vào trang ngrok
WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://d395-42-117-37-163.ngrok-free.app')

// Step 2: Click nút Visit Site
TestObject btnVisitSite = new TestObject("dynamicBtnVisitSite")
btnVisitSite.addProperty("xpath", ConditionType.EQUALS, "//div[@id='root']/div/main/div/div/section/div/footer/button")

WebUI.waitForElementClickable(btnVisitSite, 10)
WebUI.click(btnVisitSite)

// Step 3: Chuyển đến trang danh sách tour
WebUI.delay(2)
WebUI.navigateToUrl('https://d395-42-117-37-163.ngrok-free.app/tour')

// Step 4: Lưu tên tour đầu tiên (để so sánh sau khi vào chi tiết)
TestObject tourNameLink = findTestObject('List_Tour_Screen/TourItem_1_Name')
String tourName = WebUI.getText(tourNameLink)

// Step 5: Click vào tên tour
WebUI.click(tourNameLink)

// Step 6: Kiểm tra URL có chứa slug/id (tuỳ cách route trang chi tiết của bạn)
String currentUrl = WebUI.getUrl()
WebUI.verifyMatch(currentUrl, 'https:\\/\\/d395-42-117-37-163\\.ngrok-free\\.app\\/Tour\\/Detail\\/\\d+', true)

// Step 7: Kiểm tra trang chi tiết có chứa tên tour đã click
TestObject detailTitle = findTestObject('Page_TourDetail/TourTitle')
WebUI.verifyElementText(detailTitle, tourName)

// Step 8: Đóng trình duyệt
WebUI.closeBrowser()
  