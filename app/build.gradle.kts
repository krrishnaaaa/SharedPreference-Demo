plugins {
  alias(libs.plugins.android.application)
}

android {
  namespace = "com.pcsalt.example.sharedpreferencedemo"
  compileSdk = 36

  defaultConfig {
    applicationId = "com.pcsalt.example.sharedpreferencedemo"
    minSdk = 34
    targetSdk = 36
    versionCode = 2
    versionName = "2.0"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }
  }

  buildFeatures {
    viewBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

dependencies {
  implementation(libs.core.ktx)
  implementation(libs.appcompat)
  implementation(libs.material)
}
