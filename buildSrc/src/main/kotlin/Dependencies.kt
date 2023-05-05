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

    //Hilt
    private const val hiltLib = "com.google.dagger:hilt-android:${Versions.hiltLibraryVersion}"
    private const val hiltCompilerKaptLib = "com.google.dagger:hilt-android-compiler:${Versions.hiltLibraryVersion}"

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
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(testExtJUnitLib)
        add(testEspressoCoreLib)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(testJUnitLib)
    }

    val kaptLibraries = arrayListOf<String>().apply {
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