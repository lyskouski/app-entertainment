# Initiate the project

Kotlin Multiplatform is a feature of Kotlin that allows to share code between different platforms, such as Android, iOS, 
web, desktop, and more. To create a demo project for Kotlin Multiplatform, we can follow these steps:

- Download and install the latest version of IntelliJ IDEA or Android Studio with the latest Kotlin plugin.
- Clone the project template by selecting File | New | Project from Version Control in IntelliJ IDEA.
- Open the build.gradle.kts file, the build script that contains the project settings.
- Choose the platforms and targets you want to support in your project. You can use the Kotlin Multiplatform wizard to help you with this step.
- Write your shared code in the commonMain source set and your platform-specific code in the corresponding source sets, such as androidMain, iosMain, etc.
- Add dependencies to your project using the kotlin { sourceSets { … } } DSL. You can use standard, test, or kotlinx libraries, as well as third-party libraries.
- Run, test, and debug your project using the IDE tools or the command line.

We can find more information and examples about Kotlin Multiplatform on the "official documentation" or the "Kotlin blog":

: https://kotlinlang.org/docs/multiplatform.html 
: https://kotlinlang.org/docs/native-get-started.html 
: https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-getting-started.html 
: https://kotlinlang.org/docs/mpp-configure-project.html 
: https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html 
: https://kotlinlang.org/docs/mpp-share-on-platforms.html 
: https://kotlinlang.org/docs/mpp-add-dependencies.html 
: https://kotlinlang.org/docs/mpp-supported-libraries.html 
: https://kotlinlang.org/docs/mpp-run-debug.html 
: https://blog.jetbrains.com/kotlin/category/multiplatform/

Alternatively, it can be chosen any sample project from 
https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-samples.html
to build our solution on top of it.

https://github.com/JetBrains/kotlin
```
gradlew -n MyMultiplatformProject
```

KMP supports the following platforms:
- Android
- iOS
- macOS
- watchOS
- tvOS
- Windows
- Linux
- Web

While KMP provides Kotlin as the programming language, it doesn’t provide a UI. If you want to create a UI for Android, you can write it in native code or use the newer Jetpack Compose UI framework. For iOS, you can use UIKit or the newer SwiftUI framework using Swift. For the desktop, you can use Desktop Compose or Java Swing. In other words, you have a choice for how you write your UI. Many see this as an advantage — the UI will always be native, so it won’t suffer from the slow bridge communication that web-based frameworks have.

: https://www.kodeco.com/books/kotlin-multiplatform-by-tutorials/v1.0/chapters/1-introduction


For VS Code: ext install kotlin language.

After the project creation:
- shared is a Kotlin module that contains the logic common for both Android and iOS applications – the code you share between platforms. It uses Gradle as the build system to help automate your build process.

- androidApp is a Kotlin module that builds into an Android application. It uses Gradle as the build system. The androidApp module depends on and uses the shared module as a regular Android library.

- iosApp is an Xcode project that builds into an iOS application. It depends on and uses the shared module as an iOS framework. The shared module can be used as a regular framework or as a CocoaPods dependency, based on what you've chosen in the previous step in iOS framework distribution. In this tutorial, it's a regular framework dependency.

The shared module consists of three source sets: androidMain, commonMain, and iosMain. Source set is a Gradle concept for a number of files logically grouped together where each group has its own dependencies. In Kotlin Multiplatform, different source sets in a shared module can target different platforms.


To build the project it would be needed to download kotlin and make globally accessible gradlew-file execution (or, 
continue using relative path):
```
gradlew build
```

It would be needed JDK (17 or higher):
https://www.oracle.com/java/technologies/downloads/

.bashrc:

```
export JAVA_HOME="/home/vlyskouski/Programs/jdk-21.0.1/"
export PATH="$PATH:/home/vlyskouski/Programs/kotlin"
```


# First issue

First build give us an error:

  > Configure project :shared
  e: file:///home/vlyskouski/Work/_tercad/zabauka/shared/build.gradle.kts:36:30: Unresolved reference: outputKinds
  w: Missing 'androidTarget()' Kotlin target in multiplatform project 'shared (:shared)'.
  The Android Gradle plugin was applied without creating a corresponding 'android()' Kotlin Target:

```
  plugins {
      id("com.android.library")
      kotlin("multiplatform")
  }

  kotlin {
      androidTarget() // < -- please register this Android target
  }
```

The issue was relevant to the incorrect order of instructions in `shared/build.gradle.kts`. By moving `linuxX64` to the 
initial error is replaced by:

  > Task :shared:compileKotlinLinuxX64 FAILED

  FAILURE: Build failed with an exception.

  * What went wrong:
  Execution failed for task ':shared:compileKotlinLinuxX64'.

Note: https://stackoverflow.com/questions/74890057/how-to-add-desktop-support-to-kotlin-multiplatform-mobile-project


Note: Share UIs between iOS and Android. Create a Kotlin Multiplatform application that uses the Compose Multiplatform UI framework for sharing business logic and UIs among the iOS, Android, and desktop platforms.
: https://kotlinlang.org/docs/multiplatform-get-started.html#dive-deep-into-kotlin-multiplatform

With the Compose Multiplatform UI framework, you can push the code-sharing capabilities of Kotlin Multiplatform beyond application logic. You can implement the user interface once and then use it for all the platforms you target – iOS, Android, desktop, and web.


https://kmp.jetbrains.com/ - Kotlin Multiplatform Wizard


Checking the license for package Android SDK Build-Tools 33.0.1 in /home/vlyskouski/Work/_tercad/zabauka/licenses
Warning: License for package Android SDK Build-Tools 33.0.1 not accepted.
Checking the license for package Android SDK Platform 34 in /home/vlyskouski/Work/_tercad/zabauka/licenses
Warning: License for package Android SDK Platform 34 not accepted.

From the path where Android SDK has been installed (/home/vlyskouski/Android/Sdk/tools/bin/):
> sdkmanager --licenses

Or, by installing globally:
> sudo apt install sdkmanager

$ sudo sdkmanager --licenses
Downloading https://f-droid.github.io/android-sdk-transparency-log/signed/checksums.json.asc into /root/.cache/sdkmanager/checksums.json.asc
All SDK package licenses accepted.


on macOS and linux:
$ yes | sudo ~/Library/Android/sdk/tools/bin/sdkmanager --licenses

on Windows:
$ cmd.exe /C"%ANDROID_HOME%\tools\bin\sdkmanager.bat --licenses" 
