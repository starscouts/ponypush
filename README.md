# Ponypush
This is a fork of the [ntfy Android app](https://github.com/binwiederhier/ntfy) ([ntfy.sh](https://ntfy.sh)) made to work with Equestria.dev's notification servers and other technologies. It is not meant to be used outside of Equestria.dev's services.

## Build

### Building without Firebase ("F-Droid flavor", using WebSockets)
In Android Studio, go to Build > Generate Signed Bundle/APK and select `fdroidDebug` or `fdroidRelease`.

### Building with Firebase ("Google Play flavor", using Firebase Cloud Messaging)
To build your own version with Firebase, you must:
* Create a Firebase account and create an Android app on it
* Place your account file at `app/google-services.json`
* In Android Studio, go to Build > Generate Signed Bundle/APK and select `playDebug` or `playRelease`.

## Notable differences
- Revamped UI, with a lot of options removed
  - Most notably, it is not possible to use a third-party server other than notifications.equestria.dev
- Integration with Equestria.dev's tags
- Firebase Cloud Messaging support for notifications.equestria.dev

## Upstream integration
When notable changes are made upstream (on the official ntfy app), Ponypush will attempt to integrate such changes as much as possible. However, we cannot guarantee that the implemented features will work the same as with the official ntfy app.

## License
This is a fork of ntfy by [Philipp C. Heckel](https://heckel.io), distributed under the [Apache License 2.0](LICENSE) like the original project.