Looking to report an issue/bug or make a feature request? Please refer to the [README file](https://github.com/akojdadapp/mangaakojdad#issues-feature-requests-and-contributing).

---

Thanks for your interest in contributing to Manga Akojdad!

# Code Contributions

Pull requests are welcome! Follow the steps below to contribute effectively to Manga Akojdad.

## Getting Started

1. **Fork the repository**: Create a fork of the project to your GitHub account.
2. **Clone the repository**: Clone the fork to your local environment.
3. **Install dependencies**: Follow the build instructions in the [README file](https://github.com/akojdadapp/mangaakojdad#build).

If you're interested in taking on [an open issue](https://github.com/akojdadapp/mangaakojdad/issues), please comment on it so others are aware.
You do not need to ask for permission or an assignment.

## Prerequisites

Before you start, ensure you are familiar with:

- Basic [Android development](https://developer.android.com/)
- [Kotlin](https://kotlinlang.org/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)

### Tools

- [Android Studio](https://developer.android.com/studio)
- Emulator or a phone with developer options enabled to test changes.
- Access to manga APIs (if applicable).

## Submitting Your Contributions

1. Create a branch for your changes (e.g., `feature/add-manga-details`).
2. Make your changes and test them locally.
3. Submit a Pull Request to the `main` branch and explain your changes.

## Code of Conduct

Please ensure all interactions align with our [Code of Conduct](https://github.com/akojdadapp/mangaakojdad/blob/main/CODE-OF-CONDUCT.md).

---

# Translations

Translations are done externally via Weblate. See [our website](https://mangaakojdad.app/docs/contribute#translation) for more details.

We encourage contributors to review existing translations to ensure accuracy and contribute new ones.

---

# Forks

Forks are allowed so long as they abide by [the project's LICENSE](https://github.com/akojdadapp/mangaakojdad/blob/main/LICENSE).

When creating a fork, remember to:

- Avoid confusion with the main app:
  - Change the app name to reflect your fork.
  - Change the app icon to differentiate it.
  - Change or disable the [app update checker](https://github.com/akojdadapp/mangaakojdad/blob/main/app/src/main/java/com/mangaakojdad/data/updater/AppUpdateChecker.kt).
- Avoid installation conflicts:
  - Change the `applicationId` in [`build.gradle.kts`](https://github.com/akojdadapp/mangaakojdad/blob/main/app/build.gradle.kts).
- Avoid polluting the main app's analytics and crash report services:
  - Replace [`google-services.json`](https://github.com/akojdadapp/mangaakojdad/blob/main/app/src/standard/google-services.json) with your own.