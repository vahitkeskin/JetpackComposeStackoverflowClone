# Jetpack Compose StackoverflowClone

> I tried to make Jetpack Compose project belonging to Stackoverflow. I hope you will like it.

<br>

|  |  |   |   |
| ------ | ------ |---------|---------|
| ![1 image](https://user-images.githubusercontent.com/36104238/211370740-552d618a-822c-444b-8f03-ff3d7afbb815.png)|![2 image](https://user-images.githubusercontent.com/36104238/211370747-bc8e4f08-bc04-48d3-9875-30d05d2ebbec.png)| ![3 image](https://user-images.githubusercontent.com/36104238/211370752-5164de8b-c070-4546-9c17-1a5e517c4c62.png) | ![4 image](https://user-images.githubusercontent.com/36104238/211370754-7d8acc94-34dd-49ea-b592-46351f9b95d6.png)
| ![5 image](https://user-images.githubusercontent.com/36104238/211370759-f3b05d59-912f-4993-a24a-66149ba3918d.png) | ![6 image](https://user-images.githubusercontent.com/36104238/211370762-36668c12-165d-4961-ae08-212a7887131a.png)| ![7 image](https://user-images.githubusercontent.com/36104238/211370766-e20661a8-e6a2-4d98-8701-a04d43c73e3e.png) | ![8 image](https://user-images.githubusercontent.com/36104238/211370770-57546155-549a-4f6f-9478-bc949aaf9eae.png)
| ![9 image](https://user-images.githubusercontent.com/36104238/211370772-a37b374d-ac22-4519-ac46-9c931a90b878.png) | ![15 image](https://user-images.githubusercontent.com/36104238/211375700-30ed267b-368c-4806-ac0a-f7900f21b001.png) | ![15 image](https://user-images.githubusercontent.com/36104238/211626525-d20c223a-098b-4f09-aede-a2900248680c.png) | ![12 image](https://user-images.githubusercontent.com/36104238/211370781-23587080-f4ae-4625-a364-3fbcc0bfe6cb.png)
| ![13 image](https://user-images.githubusercontent.com/36104238/211370782-7a66b88d-4891-4a91-aafa-f3222bb0b596.png) | ![14 image](https://user-images.githubusercontent.com/36104238/211370785-f7611d43-016f-42dd-9c7a-425f240c98d8.png) | ![10 image](https://user-images.githubusercontent.com/36104238/211370775-fe47defc-f5dc-4f72-b296-c9bea3867a10.png)| ![11 image](https://user-images.githubusercontent.com/36104238/211370779-476f0222-82ae-47cc-98a8-3cb158b50c88.png)

- [X] Jetpack Compose
  <br><br>
    ![Developer Android - Jetpack Compose](https://user-images.githubusercontent.com/36104238/211399588-0f0756a5-98ce-4403-b61c-02f0564786e7.png)
  <br><br>
  - Jetpack Compose is Android’s recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Quickly bring your app to life with less code, powerful tools, and intuitive Kotlin APIs.<br><br>
    [![Everything Is AWESOME](https://user-images.githubusercontent.com/36104238/211627820-9e96b1bc-eaff-4b10-a3cf-8dfbfaee0d6f.png)](https://www.youtube.com/watch?v=uRfMcO7pwfk "Developer Android - Jetpack Compose")
    <br><br>
    ![My IDE](https://user-images.githubusercontent.com/36104238/211399972-e21af3af-6a59-4f2e-92f9-5ed956a8fce1.png)
- [X] Flipper
  - Flipper is a platform for debugging iOS, Android and React Native apps. Visualize, inspect, and control your apps from a simple desktop interface. Use Flipper as is or extend it using the plugin API. (Note: The device on the right does not belong to Flipper, I am using a device mirroring library called [scrcpy](https://github.com/Genymobile/scrcpy). for detailed information. Macbook's feature to get the flipper to the right.)
    <br><br>
    ![Facebook - Flipper](https://user-images.githubusercontent.com/36104238/211399983-3b7a960f-65e3-480c-aa3d-f73ae70487d0.png)
- [X] Chucker
  - Chucker simplifies the inspection of HTTP(S) requests/responses fired by your Android App. Chucker works as an OkHttp Interceptor persisting all those events inside your application, and providing a UI for inspecting and sharing their content.
  - Apps using Chucker will display a notification showing a summary of ongoing HTTP activity. Tapping on the notification launches the full Chucker UI. Apps can optionally suppress the notification, and launch the Chucker UI directly from within their own interface.
    <br><br>
    ![chucker-multiwindow](https://user-images.githubusercontent.com/36104238/211401262-a49490cd-ee45-438e-b668-8f77f4ce3984.gif)
- [X] DataStore
  - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers. DataStore uses Kotlin coroutines and Flow to store data asynchronously, consistently, and transactionally.
  - If you're currently using SharedPreferences to store data, consider migrating to DataStore instead.
    <br><br>
    ![1_3QAk_xrVEusv3mXLwfLNvw](https://user-images.githubusercontent.com/36104238/211402272-b3b3575f-6d70-4eb1-a008-c7373d9297f7.png)
- [X] Flows on Android
  - In coroutines, a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value. For example, you can use a flow to receive live updates from a database.
    <br><br>
    ![flow](https://user-images.githubusercontent.com/36104238/211402611-d9d3afba-77d3-4b33-a0f2-3a80073020d8.png)
- [X] Selecting Colors with the Palette API
  - Good visual design is essential for a successful app, and color schemes are a primary component of design. The palette library is a support library that extracts prominent colors from images to help you create visually engaging apps.
  - You can use the palette library to design layout themes and apply custom colors to visual elements in your app. For example, you can use a palette to create a color-coordinated title card for a song based on its album cover or to adjust an app’s toolbar color when its background image changes. The Palette object gives you access to the colors in a Bitmap image while also providing six main color profiles from the bitmap to help inform your design choices.
    <br><br>
    ![palette](https://user-images.githubusercontent.com/36104238/211402890-2bfab9de-e233-4dbf-9503-457362665709.png)
- [X] Dependency injection with Hilt
  - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project. Doing manual dependency injection requires you to construct every class and its dependencies by hand, and to use containers to reuse and manage dependencies.
  - Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically. Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support that Dagger provides. For more information, see Hilt and Dagger.
  - This guide explains the basic concepts of Hilt and its generated containers. It also includes a demonstration of how to bootstrap an existing app to use Hilt.
    <br><br>
    ![hilt](https://user-images.githubusercontent.com/36104238/211403240-b60a966c-4627-4cb6-8569-b234a368e137.png)
- [X] Kotlin coroutines on Android
  - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously. Coroutines were added to Kotlin in version 1.3 and are based on established concepts from other languages.
  - On Android, coroutines help to manage long-running tasks that might otherwise block the main thread and cause your app to become unresponsive. Over 50% of professional developers who use coroutines have reported seeing increased productivity. This topic describes how you can use Kotlin coroutines to address these problems, enabling you to write cleaner and more concise app code.
    <br><br>
    ![download](https://user-images.githubusercontent.com/36104238/211403780-4bde30c8-cfe2-4c6e-9039-9e03d0812319.png)
- [X] Animation
  - Jetpack Compose provides powerful and extensible APIs that make it easy to implement various animations in your app's UI. This document describes how to use these APIs as well as which API to use depending on your animation scenario.
    <br><br>
    ![ezgif com-gif-maker](https://user-images.githubusercontent.com/36104238/211404935-f136858c-75d5-4e54-86a4-3b1bce7c89b5.gif)

|  |  |
| ------ | ------ |
| ![1  gif home screen and detail open chucker](https://user-images.githubusercontent.com/36104238/211348276-3d2b9485-ac10-4695-b25d-1af1ec2a532d.gif) | ![2  gif questions full alert click close open hf](https://user-images.githubusercontent.com/36104238/211348572-18781852-e526-458e-8c91-978ad279fc16.gif)
|  |  |
| ![3  gif users and new user search](https://user-images.githubusercontent.com/36104238/211349635-dad05bcd-f89b-4fce-a8d8-cc027aa16d78.gif) | ![4  gif questions screen time 10 secs wait time finish](https://user-images.githubusercontent.com/36104238/211350203-ebf074b5-7ff2-4ccc-a874-7bdb811b051d.gif)
|  |  |
| ![5  gif questions screen switch button and data store saved](https://user-images.githubusercontent.com/36104238/211352648-5ebc335e-698e-48bf-9ed7-573e9daccead.gif) | ![6  gif questions screen item scroll and item detail code zoom](https://user-images.githubusercontent.com/36104238/211352303-37c8ea7e-523f-4b44-a121-455e6c5919d1.gif)
|  |  |
| ![7  gif new search and vahit keskin post detail open](https://user-images.githubusercontent.com/36104238/211353014-0b5ee144-a155-49ac-895e-3c7ebdf3bbbb.gif) |
