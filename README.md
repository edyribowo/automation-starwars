# Star Wars Automation Project

This repository contains automated tests for the Star Wars API (SWAPI) and the Star Wars Android Application. The project uses Java with Maven, TestNG, RestAssured for API testing, and Appium for Android automation.

## Prerequisites

Ensure you have the following installed on your machine:

- **Java JDK 25** or higher.
- **Maven** (for dependency management and running tests).
- **Appium Server** (for Android tests).
- **Android Studio** with SDK and Emulator configured (or a physical Android device).
- **Node.js** (required by Appium).

## Project Structure

- `src/test/java/org/example/api`: Contains API test classes using RestAssured.
- `src/test/java/org/example/android`: Contains Android test classes using Appium.
- `src/main/java/org/example/page`: Page Object Model (POM) classes for the Android app.
- `config.properties`: Centralized configuration for Appium capabilities and API URLs.

## Setup & Configuration

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd automation-starwars
   ```

2. **Configure `config.properties`**:
   Update the `config.properties` file in the root directory with your environment details:
   - `deviceName`: Your emulator or device ID (e.g., `emulator-5554`).
   - `app`: Path to your APK file. 
     - *Note: Ensure the APK is placed at `src/test/resources/app/org-wikipedia.apk` or update the path in the config.*
   - `appiumURL`: The URL where your Appium server is running (default: `http://127.0.0.1:4723/`).

3. **Install Dependencies**:
   ```bash
   mvn clean install -DskipTests
   ```

---

## Running API Tests

API tests validate the Star Wars API endpoints using RestAssured.

### Run All API Tests
```bash
mvn test -Dtest="org/example/api/*Test"
```

### Run Specific API Test Class
Example for People endpoint:
```bash
mvn test -Dtest=getPeopleTest
```

---

## Running Android Tests

Android tests automate the mobile application flow using Appium and the Page Object Model. Since the application is built with React Native, you must ensure the app is installed and the Metro bundler is running.

### 1. Install and Run the App (First Time)
Navigate to the `StarWarsApp` directory and install the app on your emulator/device:
```bash
cd ../StarWarsApp
npm install
npm run android
```
This step ensures the application is correctly installed and registered on your device.

### 2. Start Metro Bundler
Keep the Metro bundler running in a separate terminal to allow the React Native app to load its bundle:
```bash
cd ../StarWarsApp
npm start
```

### 3. Start Appium Server
In another terminal, start the Appium server:
```bash
appium
```

### 4. Prepare Emulator/Device
Ensure your Android emulator is running or a physical device is connected with USB debugging enabled.

### 5. Execute Automation Tests
Navigate back to the `automation-starwars` directory and run the tests:
```bash
cd automation-starwars
mvn test -Dtest="org/example/android/*Test"
```

Run a specific Android test:
```bash
mvn test -Dtest=verifyRegisterAndLoginTest
```

https://github.com/user-attachments/assets/5784cb1e-1a56-4743-b700-bd7855d5836b


---

## Test Reports

After the tests complete, you can find the execution reports in the `target` directory:

- **TestNG HTML Report**: `target/surefire-reports/index.html`
- **Command Line Summary**: Displayed directly in your terminal.

---

## Troubleshooting

- **Appium Connection**: Ensure the `appiumURL` in `config.properties` matches your running Appium server.
- **Device Not Found**: Run `adb devices` to verify your emulator or device is detected.
- **APK Path**: If you see a "File not found" error for the app, verify the `app` path in `config.properties` is correct and absolute or relative to the project root.
