import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory

// Load file dữ liệu
def testData = TestDataFactory.findTestData("Data Files/LoginTestData")

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/')

// Vòng lặp từng dòng dữ liệu
for (int i = 1; i <= testData.getRowNumbers(); i++) {
	String username = testData.getValue("UserName", i)
	String password = testData.getValue("Password", i)
	String expected = testData.getValue("ExpectedResult", i)?.trim()

	// Truy cập trang đăng nhập
	WebUI.click(findTestObject('Object Repository/Page_Login/a_Login'))

	// Nhập dữ liệu
	WebUI.setText(findTestObject('Object Repository/Page_Login/input_UserName'), username ?: "")
	WebUI.setText(findTestObject('Object Repository/Page_Login/input_Password'), password ?: "")

	// Submit
	WebUI.click(findTestObject('Object Repository/Page_Login/button_Login'))

	WebUI.delay(2)

	// Xác minh kết quả
	if (expected?.toLowerCase().contains("redirect home")) {
		WebUI.verifyMatch(WebUI.getUrl(), '.*(/|/Home).*', true)
	} else if (expected != null && expected != "") {
		WebUI.verifyTextPresent(expected, false)
	} else {
		WebUI.comment("✅ Dòng ${i}: Không có ExpectedResult, kiểm tra trạng thái giữ nguyên hoặc lỗi ngầm")
	}

	// Quay lại trang chủ để tiếp tục test
	WebUI.navigateToUrl('https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/')
}

WebUI.closeBrowser()
