# Directional Swipe Button

### Features
* Supports multi-directional swipe gestures.
* Customizable swipe button appearance.
* Built-in light animations.
* Easy integration with swipe listener support.
* Lightweight and efficient.

Before Swipe  | After swipe
-------------  | -------------  
![Before Swipe](https://github.com/user-attachments/assets/5fc8de3e-70d7-4418-8bdd-cdd2a82e7984) | ![After swipe](https://github.com/user-attachments/assets/03c4b814-d8c5-4c1a-8063-14aa7f7e4351)



## Using Directional Swipe Button Library in your Android application

Add this in your `settings.gradle`:
```groovy
maven { url 'https://jitpack.io' }
```

If you are using `settings.gradle.kts`, add the following:
```kotlin
maven { setUrl("https://jitpack.io") }
```

Add this in your `build.gradle`
```groovy
implementation 'com.github.tanimul:DirectionalSwipeButton:1.0.1'
```

If you are using `build.gradle.kts`, add the following:
```kotlin
implementation("com.github.tanimul:DirectionalSwipeButton:1.0.1")
```

### Basic Implementation:
```xml
<com.tanimul.directional_swipe_button.DirectionalSwipeButton
    android:id="@+id/swipe_btn"
    android:layout_width="match_parent"
    android:layout_height="48dp"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

### Set Swipe Listener
```kotlin
binding.swipeBtn.setOnSwipeListener(object : OnSwipeListener {
    override fun onSwipeCompleted() {
        Toast.makeText(this@MainActivity, "onSwipeCompleted", Toast.LENGTH_LONG).show()
    }
})
```
## If this library helps you in anyway, show your love :heart: by putting a :star: on this project :v:

### Contributing to AvatarX
All pull requests are welcome, make sure to follow the [contribution guidelines](CONTRIBUTING.md)
when you submit pull request.
