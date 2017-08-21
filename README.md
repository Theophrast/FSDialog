
# FSDialog (Fullscreen Styled Dialog)
[![](https://jitpack.io/v/Theophrast/FSDialog.svg)](https://jitpack.io/#Theophrast/FSDialog)

##Description
Simple wrapper library for Android Dialog, looks like Fullscreen Dialog specified in Google Material Design.

## Requirements
The Library requires **Android SDK version 26 (Android 8.0)**.


![demo1](https://github.com/Theophrast/FSDialog/blob/master/screenshots/demo1.png)
![demo1](https://github.com/Theophrast/FSDialog/blob/master/screenshots/demo2.png)

----------


## How to use FSDialog?
**Create and show the dialog**
```java
    FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
            .setTitleStringColorRes(android.R.color.white)
            .setTitleBackgroundColorRes(R.color.colorPrimaryDark)
            .setBackgroundColorRes(android.R.color.white)
            .setTitle("Title")
            .setConfirmString("Save")
            .setConfirmListener(new FSDialogButtonClickListener() {
                @Override
                public void OnButtonClick() {
                    // Replace your confirm action
                }
            })
            .setDiscardListener(new FSDialogButtonClickListener() {
                @Override
                public void OnButtonClick() {
                    // Replace your discard action
                }
            })
            .setLayoutResource(R.layout.my_dialog_content.xml);

    FSDialog dialog = builder.build();
    dialog.show();
```
Check the sample for more details.

### Set your Layout for FSDialog
Create your own layout resource file for dialog content.
For example, your own my_dialog_content.xml file looks like:
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tv_mytextview"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Dialog Content"
        android:gravity="center"
        android:layout_marginTop="32dp"
        android:layout_marginBottom="32dp"
        android:layout_centerVertical="true"
        android:layout_alignParentStart="true" />
</RelativeLayout>
```
Set your own content:
```java
 FSDialog.FsDialogBuilder builder = new FSDialog.FsDialogBuilder(this)
            ...
            .setLayoutResource(R.layout.my_dialog_content.xml);

    FSDialog dialog = builder.build();
    dialog.show();
```
Get your content View and UI elements:
```java
	View contentView = dialog.getContentView();
	TextView tv_dialog = (TextView) contentView.FindViewById(R.id.tv_mytextview);
```


Automatically dismiss the dialog on discard or confirm event (default true):
```java
	.setAutoDismiss(true);
```

## Gradle dependency
Add it in your **root build.gradle** at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
In your project level **build.gradle**:
```groovy
dependencies {
	...
	compile 'com.github.Theophrast:FSDialog:1.0'
}
```



## License
```
Copyright 2017 Janos Jakub

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

