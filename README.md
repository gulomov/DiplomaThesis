# Diploma Thesis Project

This repository contains the code for my master's thesis project, an Android application developed
using Kotlin, Jetpack Compose, MVVM architecture, and other useful tools.

## Technologies Used

<div style="display:flex; justify-content:space-between;">
  <img src="https://asset.brandfetch.io/id8oU9wOdk/idritPOXtM.png" alt="Kotlin" width="200" height="200">
  <img src="https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjC97Z8BResg5dlPqczsRCFhP6zewWX0X0e7fVPG-G7PuUZwwZVsi9OPoqJYkgqT2h0FI95SsmWzVEgpt8b8HAqFiIxZ98TFtY4lE0b8UrtVJ2HrJebRwl6C9DslsQDl9KnBIrdHS6LtkY/s1600/jetpack+compose+icon_RGB.png" alt="Jetpack Compose" width="200" height="200">
  <img src="https://geekstand.top/wp-content/uploads/2021/05/mvv.png" alt="MVVM" width="200" height="200">
  <img src="https://cdn.icon-icons.com/icons2/2699/PNG/512/firebase_logo_icon_168209.png" alt="Firebase" width="200" height="200">
  <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQC_gBfVJ7tHW6UyCrIHqK9aDPkgsBRE-2SotEM0EAbyQ&s" alt="Room Database" width="200" height="200">
</div>

- **Kotlin**: Primary programming language used for Android development.
- **Jetpack Compose**: Modern UI toolkit for building native Android UIs.
- **MVVM (Model-View-ViewModel)**: Architectural pattern used to separate the UI from the business
  logic.
- **Firebase Realtime Database**: Remote database for storing and syncing data in real-time.
- **Room Database**: Local database for persistent storage within the app.
- **Flow**: A coroutine-based API that enables a more functional and reactive approach to data
  handling.
-  **CI/CD** Project is seamlessly integrated with a CI/CD pipeline (Bitrise), ensuring efficient and automated processes for building and deploying updates. 
 
## Project Structure

### Root Directory

- **.gradle/**: Contains Gradle-specific files.
- **.idea/**: Directory for IDE-specific settings. Typically ignored in version control.
- **app/**: Main application module containing the Android app code.
    - **build/**: Contains build outputs.
    - **src/**: Source code for the application.
- **core/**: A module containing core functionalities and common resources used across the app.
    - **database/**: Manages database operations and entities.
    - **design/**: Includes design components like custom views and themes.
    - **navigation/**: Manages app navigation and routing.
    - **repository/**: Repository pattern implementations, handling data operations.
    - **sharedpreference/**: Manages shared preferences for app settings and configurations.
    - **testing/**: Contains test cases and testing utilities.
- **features/**: A module dedicated to different features of the app, following a feature-based
  structure.
    - **booking/**: Feature related to booking functionalities.
    - **common/**: Shared components and utilities used across different features.
    - **favorites/**: Manages favorite items or preferences.
    - **gallery/**: Feature for displaying and managing image galleries.
    - **home/**: Home screen and related components.
    - **introduction/**: Introduction or onboarding screens.
    - **productdetail/**: Displays detailed information about products.
    - **splash/**: Splash screen shown on app startup.
- **gradle/**: Contains Gradle wrapper files for consistent build environment setup.
    - **wrapper/**: Gradle wrapper scripts and properties.
    - **libs.versions.toml**: Configuration for managing library versions.

## Future Plans

- [ ] Learn Ktor.
- [ ] Move database from Firebase.
- [ ] Create REST APIs to load data from a remote database.
- [ ] Start migrating to Kotlin Multiplatform. (First version
  in [Compose Multiplatform](https://github.com/gulomov/DTCompseMutliplatform))
