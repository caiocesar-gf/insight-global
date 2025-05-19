# Sake Shop App 
An Android application that displays a list of Japanese sake shops, allowing users to explore details, location, and contact information for each establishment.

Architecture
The project was developed following Clean Architecture and MVVM (Model-View-ViewModel) principles, with the following layers:

Presentation
- Jetpack Compose 
- ViewModels 
- Navigation Component for navigation
- States for UI state management

Domain
- Use Cases for business rules
- Models for domain entities
- Repository interfaces
- Resource sealed class for data states

Data
- Repository implementations
- Local data sources
- DTOs for data mapping
- JSON parsing with Moshi

Jetpack Compose: Modern declarative UI framework
Material Design 3: Android design system
Coil: Image loading and caching
Navigation Compose: Screen navigation

# Dependency Injection
Hilt: Dependency injection
ViewModel + Hilt: Integration for ViewModels

# Async & Reactive
Kotlin Coroutines: Asynchronous programming
Flow: Reactive data streams
StateFlow: State management

# Data & Parsing
Moshi: JSON parsing
kotlinx.serialization: Data serialization

# Testing
JUnit: Testing framework
MockK: Mocking framework for Kotlin
Turbine: Flow testing
Truth: More readable assertions
Coroutines Test: Asynchronous code testing