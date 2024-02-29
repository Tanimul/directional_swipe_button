plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id ("maven-publish")
}

android {
    namespace = "com.tanimul.directional_swipe_button"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}


afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                // Add dependencies directly
                from(components["release"])

                // Specify publication information
                groupId = "com.github.tanimul"
                artifactId = "directional_swipe_button"
                version = "1.0.0"
            }
        }
    }
}