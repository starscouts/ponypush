# Ponypush
This app started as a fork of the [ntfy Android app](https://github.com/binwiederhier/ntfy) ([ntfy.sh](https://ntfy.sh)) made to work with Equestria.dev's notification servers. It is not meant to be used outside of Equestria.dev and will not work without the Equestria.dev servers.

## Build
In Android Studio, go to Build > Generate Signed Bundle/APK and select `debug` or `release`.

## Notable differences with upstream ntfy
- Revamped UI, with a lot of options removed
  - Most notably, it is not possible to use a third-party server other than notifications.equestria.dev
- Integration with Equestria.dev's tags
- Targets Android 14 instead of the upstream Android 13
- Requires Android 9 or later instead of Android 5.0 or later
- Up-to-date dependencies

## Upstream integration
When notable changes are made to the official ntfy app, Ponypush will attempt to integrate such changes as much as possible. However, we cannot guarantee that the implemented features will work the same as with the official ntfy app.

## License
This is a fork of ntfy by [Philipp C. Heckel](https://heckel.io), originally distributed under the Apache License 2.0. This modified application is released under the [GNU Affero General Public License version 3](LICENSE) as per Equestria.dev's policy. 