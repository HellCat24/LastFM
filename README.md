LastFM
====================================================================

Architecture - MVP
Main Frameworks - Rx-Java, Dagger 2, GreenDao

====================================================================

Short Architecture Description

App is divided into two modules:

- Data module is fully responsible for storing, requesting and processing
all kinds of entities within the app. Contains stores, repositories, API,
 db, DAO and mappers classes. The Repository Pattern was used for interactions with app's data.
 Local and Remote cache states in Repository were added for the future app development.

- App module is a module responsible for UI part of the app. Contains presenters,
activities, and dagger. Dagger was used to implement dependency injection.
This enabled to make app's components more independent and testable.

====================================================================

Other Notes:

- UI tests is absent due to the simplicity of the app and time limitations

- Connection errors handle pretty loosely - app shows connecting error on any request

- Activities orientation was locked to android:screenOrientation="portrait" due to time limitations

- Artist list sorting was implemented only as part the initial task, sorting should be handled by a server

- CompositeDisposable were used to handle activity lifecycle within rx-java observables