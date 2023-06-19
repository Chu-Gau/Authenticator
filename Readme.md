Example usage:
```batch
adb shell am start -n com.aurora.authenticator/.EmptyActivity & adb shell content query --uri content://com.aurora.authenticator.provider/auth
```