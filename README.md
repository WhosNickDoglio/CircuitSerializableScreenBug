# Circuit Serializable bug

Reproducer for https://github.com/slackhq/circuit/issues/2838

Trunk contains the bug and I tested parcelize in the ndoglio/parcel branch and manually setting up
the `SavedStateConfiguration`
in ndoglio/manual, neither could reproduce the issue again.

Steps to reproduce:

1. Launch sample project
2. Click Login button
3. Background the app (Press home button)
4. Observe crash in background (might only be visible in logcat)
5. Reopen the app and observe it opens on the home screen and not the login screen. 
