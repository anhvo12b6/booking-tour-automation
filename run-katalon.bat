@echo off
REM === Chạy test Katalon CLI tự động (dùng cho Jenkins) ===

:: Đường dẫn đến file .prj (project chính)
set "PROJECT_PATH=%cd%\BookingTour.prj"

:: API Key của bạn (lấy từ https://analytics.katalon.com)
set "API_KEY=92a3937d-d5a9-402f-b5f5-0a0506b6f51b"

:: Đường dẫn đến Katalon CLI (nếu có sẵn katalonc.exe trong máy bạn)
set "KATALON_EXE=C:\Users\hoanganhvo8\Downloads\Katalon_Studio_Engine_Windows_64-10.2.2\katalonc.exe"

:: Test Suite bạn muốn chạy
set "TEST_SUITE=Test Suites/TC_Register_success"

:: Thư mục lưu report
set "REPORT_DIR=Reports"

:: Gọi lệnh thực thi Katalon CLI
"%KATALON_EXE%" -noSplash -runMode=console ^
 -projectPath="%PROJECT_PATH%" ^
 -testSuitePath="%TEST_SUITE%" ^
 -browserType="Chrome" ^
 -apiKey="%API_KEY%" ^
 -reportFolder="%REPORT_DIR%" ^
 -reportFileName="report" ^
 -exportReport=true

exit /b %ERRORLEVEL%

