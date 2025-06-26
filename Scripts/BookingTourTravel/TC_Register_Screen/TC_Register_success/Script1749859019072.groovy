import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory

// Load file dữ liệu
def testData = TestDataFactory.findTestData("Data Files/TestData")

WebUI.openBrowser('')
WebUI.maximizeWindow()
WebUI.navigateToUrl('https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/')

// Lặp từng dòng dữ liệu (bỏ header)
for (int i = 1; i <= testData.getRowNumbers(); i++) {
    String username = testData.getValue("UserName", i)
    String email = testData.getValue("Email", i)
    String password = testData.getValue("Password", i)
    String expected = testData.getValue("ExpectedResult", i)?.trim()

    // Truy cập trang đăng ký
    WebUI.click(findTestObject('Object Repository/Page_Register/a_NG K'))

    // Nhập dữ liệu
    WebUI.setText(findTestObject('Object Repository/Page_Register/input_Register_UserName'), username ?: "")
    WebUI.setText(findTestObject('Object Repository/Page_Register/input_Register_Email'), email ?: "")
    WebUI.setText(findTestObject('Object Repository/Page_Register/input_Register_Password'), password ?: "")

    // Tick checkbox
    WebUI.click(findTestObject('Object Repository/Page_Register/input_Register_exampleCheck'))

    // Submit
    WebUI.click(findTestObject('Object Repository/Page_Register/input_I have read and accept the Terms and _1ec15a'))

    WebUI.delay(2)

    // Xác minh kết quả
    if (expected?.toLowerCase().contains("redirect home")) {
        WebUI.verifyMatch(WebUI.getUrl(), '.*Account/Login.*', true)
    } else if (expected != null && expected != "") {
        WebUI.verifyTextPresent(expected, false)
    } else {
        // Nếu không có ExpectedResult, kiểm tra không chuyển trang
        def currentUrl = WebUI.getUrl()
        if (!currentUrl.contains("Login")) {
            WebUI.comment("✅ Dòng ${i}: Không chuyển trang, ở lại form đăng ký như mong đợi")
        }
    }

    // Trở lại trang chủ trước khi test dòng tiếp
    WebUI.navigateToUrl('https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/')
}

WebUI.closeBrowser()
