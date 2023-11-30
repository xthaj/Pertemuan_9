package com.polstat.digilib.ui

import android.app.Activity
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ShareCompat
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.polstat.digilib.ui.screen.BookCollectionScreen
import com.polstat.digilib.ui.screen.WelcomeScreen
import com.polstat.digilib.ui.screen.LoginScreen
import com.polstat.digilib.ui.screen.RegisterScreen
import com.polstat.digilib.ui.screen.BookDetailScreen
import com.polstat.digilib.ui.screen.dummyData
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun DigilibApp() {
    val navController = rememberNavController()
    DigilibNavHost(navController = navController)
}
@Composable
fun DigilibNavHost(navController: NavHostController) {
    val books = dummyData()
    val activity = (LocalContext.current as Activity)
    NavHost(navController = navController, startDestination = "welcome") {
        composable("home") {
            BookCollectionScreen(
                books = books,
                onBookClick = {
                    navController.navigate("books/${it.id}")
                })
        }
        composable(
            "books/{bookid}",
            arguments = listOf(navArgument("bookid") {
                type = NavType.IntType
            })
        ) {
            val id = it.arguments?.getInt("bookid")
            var book = books.get(id!!)
            BookDetailScreen(
                book = book,
                onBackClick = { navController.navigateUp() },
                onShareClick = {
                    createShareIntent(activity, it)
                })
        }

        composable("welcome") {
            WelcomeScreen(navController = navController)
        }

        composable("login") {
            LoginScreen(navController = navController)
        }


        composable("register") {
            RegisterScreen(navController = navController)
                }
        }
}

fun createShareIntent(activity: Activity, bookTitle: String) {
    val shareText = bookTitle
    val shareIntent = ShareCompat.IntentBuilder(activity)
        .setText(shareText)
        .setType("text/plain")
        .createChooserIntent()
        .addFlags(
            Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
    activity.startActivity(shareIntent)
}
