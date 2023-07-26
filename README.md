# Project description

This is a sample project that demonstrates modern approach of Android application development using [Kotlin](https://kotlinlang.org/) and latest stack.

# Module Design

Modularization implementation according to the latest guides from Google [Here](https://developer.android.com/topic/modularization/patterns) and [Here](https://developer.android.com/topic/modularization)
## Example of structure
<img src="/art/modules_structure.png" width="75%" height="75%" />

## Modules list
| Module name                         | Type                | Description                                                              |
|-------------------------------------|---------------------|--------------------------------------------------------------------------|
| [app](/app)                         | Android Application | MainActivity, BaseApplication, Theme, Navigation Implementation, DI.     |
| [core:base](/core/base)             | Android Library     | Base extentions, common Composables, Base MVI components and navigation. |
| [core:network](/core/network)       | Android Library     | Http client configuration, interceptors and API contract description.    |
| [feature:auth](/feature/auth)       | Android Library     | Auth screens and components, ViewModels and State description.           |
| [feature:reddits](/feature/reddits) | Android Library     | Reddit screens and components, ViewModels and State description.         |
| [data:auth](/data/auth)             | Android Library     | Auth repositories, models and use-cases                                  |
| [data:reddits](/data/reddits)       | Android Library     | Reddits repositories, models and use-cases                               |
| [data:storage](/data/storage)       | Android Library     | Database configuration, dao's and Entity models                          |
| [data:common](/data/common)         | Java/Kotlin Library | Common interfaces, models and constants                                  |

## Project characteristics

Tools and solutions and tech-stack:

* [Kotlin](https://kotlinlang.org/)
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Android Jetpack](https://developer.android.com/jetpack)
* [Room Persistence Library](https://developer.android.com/topic/libraries/architecture/room)
* [Ktor](https://ktor.io/docs/welcome.html)
* [Hilt Android](https://developer.android.com/training/dependency-injection/hilt-android)
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [MVI](https://medium.com/@VolodymyrSch/android-simple-mvi-implementation-with-jetpack-compose-5ee5d6fc4908)
* [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)
* [Material design 3](https://developer.android.com/jetpack/compose/designsystems/material3)
* [Coil](https://coil-kt.github.io/coil/compose/)
* [Shimmer](https://github.com/valentinilk/compose-shimmer)
* A single-activity architecture
* Architecture
    * `Clean Architecture`
    * `MVI`
    * [Jetpack Compose](https://developer.android.com/jetpack/compose)

# build.gradle files
There are 3 types of build.gradle files.
1. android application (app module)
2. android-feature-build.gradle - Android module that contains ui components.
3. android-library-build.gradle - Android module that contains android specific code(access to Context ect.).
4. library-build.gradle - Pure java/kotlin library.