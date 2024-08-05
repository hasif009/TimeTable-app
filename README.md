# TimeTable-app
A Simple Android application that displays the timetable listing of Departures of station

# Features
## Functional features
- Fetch Data from Server 
- Offline support : Storing Data into the DB .
## Non functioanl features
- Performance: Response time, load time, resource utilization   
- Usability: User interface design, navigation, error handling

# Architecture
As this is an MVP with SIngle screen and one functionality , the scope of the project is confined to Single module setup. The Project uses MVVM pattern to implement the feature. The packages are structured in such a way that in a larger scope , each package could be transformed into Multi Module setup with each module comprising of two sub modules 
- **API**  : Module that defines the contracts (Interfaces and Data classes)
- **Implementation** : Module that implements the contracts defined in the API  and internal implementation of the module . The implementation of interfaces in API module would be exposed via Dependency injection Framework(like Dagger)

so in this project , if we make this multi module , delivery-list could be one feature module , and common-ui, network etc could be other modules. every module could expose its API module to other dependent modules to access data.

# Test setup
The project implements following test framework
- **Unit test** : Using mock library to mock the classes and test core bussiness functionality of the app.
- **UI test** : Integration test using Espresso framework ,and MockWebServer to mock the API locally and assert various success and failure scenarios and verify the UI behaviour in the app

# Technical features implemented
- Full compose Screens
- Compose Navigation 
- Dependency injection (Hilt)
- Retrofit and OkHttp for network calls
- coroutines and flow usage
- UI test with Espresso, Kakao and Kaspresso for compose DSL 
- Room Db for Offline support



