import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// Step 1: Mở trình duyệt và vào trang ngrok
WebUI.openBrowser('')

WebUI.maximizeWindow()

WebUI.navigateToUrl('https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/')


// Step 3: Chờ trang chính load và chuyển tới trang danh sách tour
WebUI.delay(2)

WebUI.navigateToUrl('https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/tour')

// Step 4: Kiểm tra phần tử tour đầu tiên hiển thị
WebUI.verifyElementPresent(findTestObject('List_Tour_Screen/TourItem_1_Name'), 10)

// Step 5: Kiểm tra các thông tin chính
String tourName = WebUI.getText(findTestObject('List_Tour_Screen/TourItem_1_Name'))

WebUI.verifyMatch(tourName, '.*', true)

String category = WebUI.getText(findTestObject('List_Tour_Screen/TourItem_1_Category'))

WebUI.verifyMatch(category, '.*', true)

String duration = WebUI.getText(findTestObject('List_Tour_Screen/TourItem_1_Duration'))

WebUI.verifyMatch(duration, 'Thời gian tour: .*', true)

String price = WebUI.getText(findTestObject('List_Tour_Screen/TourItem_1_Price'))

WebUI.verifyMatch(price, '.*VNĐ', true)

// Step 7: Đóng trình duyệt
WebUI.closeBrowser()

