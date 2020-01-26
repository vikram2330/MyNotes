# MyNotes
Sample notes application using Room, MVVM, Coroutines, Dagger2
## Code Style

### Kotlin
Please reference this:  https://developer.android.com/kotlin/style-guide

### Naming
**Layout component IDs**
The IDs should be snake_case

example:
`android:id="@+id/global_toolbar_location_spinner"`

**colors.xml**

Color names should be camelCase

example:
`<color name="colorPrimary">#050F26</color>`

**styles.xml**

Style block names should be PascalCase  (aka AlpacaCase lol)

example:
`<style name="SpinnerItemWhatever">
   <item name="android:fontFamily">@font/solis_regular</item>
   <item name="android:textColor">@color/primaryTextColor</item>
   <item name="android:textSize">16sp</item>
</style>`

Base styles should be prefixed with Base

`<style name="BaseText">
   <item name="android:fontFamily">@font/solis_regular</item>
</style>`

**dimens.xml**

Dimension names should be snake_case

example:
`<dimen name="activity_horizontal_margin">16dp</dimen>`


**strings.xml**

String names should be snake_case

example:
`<string name="menu_bottom_navigation_daily">Daily</string>`

## Gitflow & PR 

Use the gitflow model for feature work, bug fixes, and releases.

