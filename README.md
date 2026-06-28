# ROM Info App

A clean, Material You (Material 3) Android app to show ROM information, changelog, maintainer details and links.  
Designed for **Android 14+** (targets Android 16 QPR2).  
Ships as a **non-deletable system app** via vendor prebuilt.

---

## ✏️ How to Customize (Edit your info)

Open this file and fill in your details:

```
app/src/main/assets/rom_config.json
```

### Example:
```json
{
  "rom": {
    "name": "MyAwesomeROM",
    "version": "2.0",
    "android_version": "16 QPR2",
    "build_type": "OFFICIAL",
    "build_date": "2025-06-28",
    "build_variant": "GAPPS",
    "device_name": "Xiaomi 14",
    "device_codename": "aurora",
    "architecture": "arm64-v8a",
    "security_patch": "June 2025"
  },
  "maintainer": {
    "name": "Your Name",
    "telegram": "@yourtg",
    "github": "yourgithub",
    "xda_thread": "https://xdaforums.com/your-thread",
    "donation_url": "https://paypal.me/yourlink"
  },
  "links": {
    "telegram_group": "https://t.me/yourgroup",
    "telegram_channel": "https://t.me/yourchannel",
    "github_repo": "https://github.com/yourgithub/rom",
    "sourceforge": "https://sourceforge.net/projects/yourrom/"
  },
  "changelog": [
    {
      "version": "2.0",
      "date": "2025-06-28",
      "changes": [
        "Based on Android 16 QPR2",
        "Updated security patch to June 2025",
        "Performance improvements",
        "Fixed notification issues"
      ]
    },
    {
      "version": "1.0",
      "date": "2025-05-01",
      "changes": [
        "Initial release"
      ]
    }
  ]
}
```

---

## 🏗️ Build the APK

### Requirements
- Android Studio Hedgehog or newer
- JDK 17

### Steps
```bash
git clone https://github.com/yourgithub/ROMInfo
cd ROMInfo
# Edit app/src/main/assets/rom_config.json with your info
./gradlew assembleRelease
# APK will be at: app/build/outputs/apk/release/app-release-unsigned.apk
```

Sign the APK with your ROM's platform key:
```bash
java -jar signapk.jar platform.x509.pem platform.pk8 \
  app-release-unsigned.apk ROMInfo.apk
```

---

## 📦 Include as System App (Non-deletable)

### Step 1 — Place files in your vendor folder:
```
vendor/<romname>/prebuilt/ROMInfo/
    ├── Android.mk
    ├── rominfo.mk
    └── prebuilt/
        └── ROMInfo.apk      ← Put your signed APK here
```

Copy from this repo:
```bash
cp -r vendor-integration/ vendor/<romname>/prebuilt/ROMInfo/
cp ROMInfo.apk vendor/<romname>/prebuilt/ROMInfo/prebuilt/
```

### Step 2 — Add to your common.mk:
```makefile
# In vendor/<romname>/config/common.mk
$(call inherit-product-if-exists, vendor/<romname>/prebuilt/ROMInfo/rominfo.mk)
```

### Step 3 — Build your ROM 🎉

---

## 📱 Features
- 📋 ROM Information (version, build type, date, variant)
- 📱 Device Information (name, codename, arch, kernel, security patch)
- 👤 Maintainer Info with quick-links to Telegram / GitHub / XDA
- 📝 Full Changelog with version history
- 🔗 Download & community links
- 🎨 Material You / Material 3 dynamic color
- 🌙 Auto dark/light mode
- 🚫 Cannot be deleted by user (system app)

---

## 📁 File Structure
```
ROMInfo/
├── app/src/main/
│   ├── assets/
│   │   └── rom_config.json        ← EDIT THIS
│   ├── java/com/rominfo/app/
│   │   ├── MainActivity.java
│   │   ├── data/RomConfig.java
│   │   ├── utils/ConfigLoader.java
│   │   └── ui/screens/
│   │       ├── AboutFragment.java
│   │       ├── ChangelogFragment.java
│   │       └── LinksFragment.java
│   └── res/layout/
│       ├── activity_main.xml
│       ├── fragment_about.xml
│       ├── fragment_changelog.xml
│       └── fragment_links.xml
└── vendor-integration/            ← Copy to your vendor folder
    ├── Android.mk
    ├── rominfo.mk
    └── prebuilt/                  ← Put signed APK here
```

---

## 📜 License
Apache 2.0
