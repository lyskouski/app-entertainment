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

For VS Code: ext install kotlin language.