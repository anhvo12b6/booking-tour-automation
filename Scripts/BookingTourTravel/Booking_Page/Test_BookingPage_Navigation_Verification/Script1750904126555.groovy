import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

// 1. Mở trình duyệt và truy cập trang chi tiết tour (tourId = 1 là ví dụ)
WebUI.openBrowser('')
WebUI.maximizeWindow()

String tourDetailUrl = 'https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/Tour/Detail/1'
WebUI.navigateToUrl(tourDetailUrl)

// 2. TC01 - Click vào nút "Đặt ngay"
WebUI.click(findTestObject('Object Repository/Page_Booking/a_t ngay'))

String expectedUrl = 'https://booking-tour-2025-evfcbegkgjbxawcn.southeastasia-01.azurewebsites.net/Booking/Create/1'
String currentUrl = WebUI.getUrl()
WebUI.verifyMatch(currentUrl, expectedUrl, false)
// 4. TC02 - Xác minh các nội dung hiển thị trên trang đặt tour

def actualTourName = WebUI.getText(findTestObject('Object Repository/Page_Booking/h5_Ph Yn - Quy Nhn - Eo Gi - K Co (Khch sn 4 sao)'))
WebUI.verifyMatch(actualTourName, '(?i)Phú Yên - Quy Nhơn - Eo Gió - Kỳ Co \\(Khách sạn 4 sao\\)', true)

// So khớp ngày khởi hành (giả sử định dạng là dd/MM/yyyy)
WebUI.verifyElementText(findTestObject('Object Repository/Page_Booking/span_27062025'), '27/06/2025')

// So khớp giá tour
WebUI.verifyElementText(findTestObject('Object Repository/Page_Booking/span_7,390,000 VN'), '7,390,000 VNĐ')

// Đóng trình duyệt
WebUI.closeBrowser()
