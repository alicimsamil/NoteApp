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

    //Hilt
    private const val hiltLib = "com.google.dagger:hilt-android:${Versions.hiltLibraryVersion}"
    private const val hiltCompilerKaptLib = "com.google.dagger:hilt-android-compiler:${Versions.hiltLibraryVersion}"

    //Room
    private const val roomLib= "androidx.room:room-runtime:${Versions.roomVersion}"
    private const val roomCompilerLib= "androidx.room:room-compiler:${Versions.roomVersion}"
    private const val roomTestLib= "androidx.room:room-testing:${Versions.roomVersion}"
    private const val roomKtxLib= "androidx.room:room-ktx:${Versions.roomVersion}"

    //Coroutines
    private const val coroutinesLib = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    private const val coroutinesTestLib = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesVersion}"

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
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(testExtJUnitLib)
        add(testEspressoCoreLib)
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