import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * This class keeps dependencies.
 */
object Dependencies {

    //app
    private const val materialLib= "com.google.android.material:material:${Versions.materialLibVersion}"
    private const val coreKtxLib = "androidx.core:core-ktx:${Versions.coreLibVersion}"
    private const val appCompatLib = "androidx.appcompat:appcompat:${Versions.appcompatLibVersion}"
    private const val constraintLayoutLib = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    private const val dataBindingLib = "androidx.databinding:databinding-runtime:${Versions.dataBindingVersion}"
    private const val viewModelLib = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.viewModelVersion}"
    private const val activityLib = "androidx.activity:activity-ktx:${Versions.activityVersion}"


    //Hilt
    private const val hiltLib = "com.google.dagger:hilt-android:${Versions.hiltLibraryVersion}"
    private const val hiltCompilerKaptLib = "com.google.dagger:hilt-android-compiler:${Versions.hiltLibraryVersion}"
    private const val hiltTestingLib = "com.google.dagger:hilt-android-testing:${Versions.hiltLibraryVersion}"

    //Room
    private const val roomLib= "androidx.room:room-runtime:${Versions.roomVersion}"
    private const val roomCompilerLib= "androidx.room:room-compiler:${Versions.roomVersion}"
    private const val roomTestLib= "androidx.room:room-testing:${Versions.roomVersion}"
    private const val roomKtxLib= "androidx.room:room-ktx:${Versions.roomVersion}"
    private const val roomPagingLib = "androidx.room:room-paging:${Versions.roomVersion}"

    //Paging
    private const val pagingLib = "androidx.paging:paging-common-ktx:${Versions.pagingVersion}"

    //Coroutines
    private const val coroutinesLib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    private const val coroutinesTestLib = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

    //Splash Screen
    private const val splashScreenLib = "androidx.core:core-splashscreen:${Versions.splashScreenVersion}"

    //test
    private const val testJUnitLib = "junit:junit:${Versions.testJunitVersion}"
    private const val testExtJUnitLib = "androidx.test.ext:junit:${Versions.testExtJunitVersion}"
    private const val testEspressoCoreLib = "androidx.test.espresso:espresso-core:${Versions.testEspressoVersion}"

    val appLibraries = arrayListOf<String>().apply {
        add(coreKtxLib)
        add(appCompatLib)
        add(constraintLayoutLib)
        add(materialLib)
        add(hiltLib)
        add(coroutinesLib)
        add(dataBindingLib)
        add(roomLib)
        add(roomKtxLib)
        add(roomPagingLib)
        add(pagingLib)
        add(splashScreenLib)
        add(viewModelLib)
        add(activityLib)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(testExtJUnitLib)
        add(testEspressoCoreLib)
        add(hiltTestingLib)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(testJUnitLib)
        add(coroutinesTestLib)
        add(roomTestLib)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(hiltCompilerKaptLib)
        add(roomCompilerLib)
    }

    val annotationLibraries = arrayListOf<String>().apply {
        add(roomCompilerLib)
    }

    val kaptTestLibraries = arrayListOf<String>().apply {
        add(hiltCompilerKaptLib)
    }

    val kaptAndroidTestLibraries = arrayListOf<String>().apply {
        add(hiltCompilerKaptLib)
    }

}

/**
 * This function adds kapt dependencies sequentially.
 */
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

/**
 * This function adds kaptTest dependencies sequentially.
 */
fun DependencyHandler.kaptTest(list: List<String>) {
    list.forEach { dependency ->
        add("kaptTest", dependency)
    }
}

fun DependencyHandler.kaptAndroidTest(list: List<String>) {
    list.forEach { dependency ->
        add("kaptAndroidTest", dependency)
    }
}

/**
 * This function adds implementation dependencies sequentially.
 */
fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

/**
 * This function adds androidTestImplementation dependencies sequentially.
 */
fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

/**
 * This function adds testImplementation dependencies sequentially.
 */
fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

/**
 * This function adds annotationProcessor dependencies sequentially.
 */
fun DependencyHandler.annotationProcessor(list: List<String>) {
    list.forEach { dependency ->
        add("annotationProcessor", dependency)
    }
}