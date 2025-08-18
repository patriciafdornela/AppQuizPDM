//package np.com.bimalkafle.quizonline.login
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import np.com.bimalkafle.quizonline.login.page.HomePage
//import np.com.bimalkafle.quizonline.login.page.LoginPage
//import com.example.myapplication1.pages.SignupPage
//
//@Composable
//fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login", builder = {
//        composable("login"){
//            LoginPage(modifier, navController, authViewModel)
//        }
//        composable("signup"){
//            SignupPage(modifier, navController, authViewModel)
//        }
//        composable("home"){
//            HomePage(modifier, navController, authViewModel)
//        }
//    })
//}