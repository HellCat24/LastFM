LastFM
==================================

Architecture - MVP
Main Frameworks - Rx-Java, Dagger 2, GreenDao

Short Architecture Solution Description

App is divided in two modules

- Data is fully responsible for storing, requesting and processing
all kinds of entities within the app. Contains stores, repositories, api,
 db, DAO and mappers classes. The Repository Pattern was used for interactions
 with app's data.

- App is main module responsible for UI part of the app. Contains presenters,
activities and dagger part. Dagger was uses to implement dependency injection.
This enabled to make app's components more independent and testable.