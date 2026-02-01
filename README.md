# Seekho-Jikan

### A Production-Perfect Android Anime Application

Seekho-Jikan is a power-packed, resilient Android application that serves as a premium gateway to the world of Anime using the Jikan API.  
Built with a **Production-First** mindset, the app demonstrates how to handle real-world challenges such as intermittent connectivity and scalable software architecture.

---

### Architectural Excellence

This project is built using **Clean Architecture** combined with the **MVVM (Model–View–ViewModel)** pattern, ensuring strict separation of concerns and high testability.

- **Presentation Layer:**  
  100% Jetpack Compose with a state-driven UI and Material 3 design.

- **Domain Layer:**  
  Core business logic encapsulated in Use Cases to orchestrate data flow and enforce single responsibility.

- **Data Layer:**  
  Repository Pattern with a **Single Source of Truth (SSOT)** using Room.

- **Dependency Injection:**  
  Hilt for standardized, compile-time-safe dependency management across all layers.

---

### Key Production Features

#### Advanced Offline-First Logic

Unlike standard apps that fail without a signal, Seekho-Jikan uses a robust **Network-Bound Resource** strategy.

- **Room Persistence:**  
  Local SQL storage for anime lists and detailed data.

- **Reactive Network Monitoring:**  
  Uses `callbackFlow` with `ConnectivityManager` to detect network changes in real time.

- **Silent Fallback:**  
  Cached data is served seamlessly when offline or when the API fails.

- **Smart Error Handling:**  
  “No Data” screens appear only when both network and cache are empty, with a retry mechanism.

---

#### Immersive Media Experience

- **Hybrid Media Player:**  
  Dynamically switches between YouTube Player API for trailers and Glide-based poster rendering.

- **Cinematic UI:**  
  GraphicsLayer blur effects, hardware-accelerated gradients, and marquee titles.

- **Shimmer Loading:**  
  Custom shimmer effects during data fetch for a premium loading experience.

---

### Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose (Material 3)
- **Architecture:** Clean Architecture + Use Cases + MVVM
- **DI:** Hilt
- **Networking:** Retrofit + OkHttp
- **Local Database:** Room
- **Media:** YouTube IFrame Player API
- **Image Loading:** Glide / Landscapist (Shimmer & Blur support)
- **Reactive Streams:** Kotlin StateFlow
- **Compose Navigation:** Implemented using a centralized navigation graph for scalable and maintainable screen transitions.

---

### Features Implemented

- **Home Screen (Anime List):**
    - Displays top anime with titles, episode counts, ratings, and poster images.
    - Uses an attractive card-based layout with a black gradient tile pattern for a visually rich and eye-soothing UI.

- **Detail Screen:**
    - Auto-playing YouTube trailer background with fallback to blurred poster.
    - Full synopsis, episode count, ratings, and metadata.
    - Expandable text for long descriptions.

- **Navigation Drawer:**
    - Implemented a navigation drawer to enhance overall UI structure and user experience.
    - Serves as a scalable entry point for future features and sections.

- **Navigation:**  
  Jetpack Navigation with type-safe route handling.

- **Responsive Orientation Handling:**
  The UI remains consistent and does not get distorted during screen rotations, ensuring a seamless user experience across configuration changes.

- **Theming:**  
  Full Material 3 support with Light and Dark mode.

- **Offline Support:**  
  Works completely offline using cached data and syncs automatically when connectivity is restored.

-  **Edge Case Handling:**
  Proper handling of edge cases such as empty states and network connectivity changes using an internet listener to ensure a stable and user-friendly experience.
---

### Assumptions & Implementation Details

- **YouTube Integration:**  
  Since Jikan trailers are YouTube-based, YouTube Player was used due to ExoPlayer limitations with raw YouTube URLs.

- **Data Sync Strategy:**  
  Local cache is prioritized for performance; network refresh happens only when connectivity is confirmed.

- **UI Resilience:**  
  A shared BaseScreen handles global UI concerns like app bars, snackbars, and no-internet states to reduce boilerplate.

---

### Known Limitations

- **Media Performance:**  
  Media is failing due to which video support is not properly tested.

  **Error Handling & Testing:**
- Error handling and test coverage can be further improved to make the application more robust and resilient in real-world scenarios.

- **TDD Approach:**
  Test-Driven Development (TDD) was not followed due to time constraints for this assignment.

- **Navigation Drawer:**
  Navigation Drawer's gesture is kept disabled right now.

- **Pagination:**
  Pagination is not implemented as it was not part of the given task.  
  However, it can be easily achieved using Jetpack Compose pagination by passing the `page` query parameter to the Jikan API.
