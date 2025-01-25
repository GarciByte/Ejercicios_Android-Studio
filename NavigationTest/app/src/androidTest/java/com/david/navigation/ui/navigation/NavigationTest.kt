package com.david.navigation.ui.navigation

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.isToggleable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import com.david.navigationtest.ui.navigation.ui.navigation.ArgsKeys
import com.david.navigationtest.ui.navigation.ui.navigation.NavGraph
import com.david.navigationtest.ui.navigation.ui.navigation.NavRoute
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    // Crear una regla de prueba
    val composeTestRule = createComposeRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        // Crear un TestNavHostController
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            NavGraph(navController = navController)
        }
    }

    @Test
    fun assert_IsMainScreenDestinationRouteRight() {
        // Verifica que la primera pantalla sea la principal (MainScreen)
        val currentDestination = navController.currentBackStackEntry?.destination?.route
        Truth.assertThat(currentDestination).isEqualTo(NavRoute.NavMainScreen.route)
    }

    @Test
    fun assert_IsStartDestinationRouteRight() {
        // Verifica que la primera pantalla configurada en el gráfico de navegación
        // coincide con la ruta actual
        val startDestination = navController.graph.startDestinationRoute
        val currentDestination = navController.currentBackStackEntry?.destination?.route
        Truth.assertThat(currentDestination).isEqualTo(startDestination)
    }

    @Test
    fun navigateToSecondScreen_Assert_IsSecondScreenDestinationRouteRight() {
        // Navega a la SecondScreen y verifica que la ruta del destino sea correcta
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick() // Checkbox
        composeTestRule.onNodeWithText("SecondScreen").performClick()

        val currentDestination = navController.currentBackStackEntry?.destination?.route
        Truth.assertThat(currentDestination).isEqualTo(NavRoute.NavSecondScreen.route)
    }

    @Test
    fun navigateToSecondScreen_Assert_AreSecondScreenArgumentsCorrect() {
        // Navega a la SecondScreen y verifica que los argumentos pasados sean correctos
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()

        val currentBackStackArgs = navController.currentBackStackEntry?.arguments
        val name = currentBackStackArgs?.getString(ArgsKeys.NAME)
        val isOverEighteen = currentBackStackArgs?.getBoolean(ArgsKeys.IS_OVER_EIGHTEEN)

        Truth.assertThat(name).isEqualTo("Carl")
        Truth.assertThat(isOverEighteen).isEqualTo(true)
    }

    @Test
    fun navigateToSecondScreen_And_PressBackButton_Assert_IsMainScreenDestinationRouteRight() {
        // Navega a la SecondScreen, presiona "Atrás" y verifica que vuelva a MainScreen
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()

        Espresso.pressBack()

        val currentDestination = navController.currentBackStackEntry?.destination?.route
        Truth.assertThat(currentDestination).isEqualTo(NavRoute.NavMainScreen.route)
    }

    @Test
    fun navigateToThirdScreen_And_PopBackStackToSecondScreen_Assert_IsThirdScreenNotInBackStack() {
        // Navega a la ThirdScreen, vuelve a la SecondScreen y verifica que ThirdScreen no está en el BackStack
        composeTestRule.onNodeWithText("Name").performTextInput("Carl")
        composeTestRule.onNode(isToggleable()).performClick()
        composeTestRule.onNodeWithText("SecondScreen").performClick()
        composeTestRule.onNodeWithText("ThirdScreen").performClick()

        composeTestRule.onNodeWithText("SecondScreen").performClick()

        val isThirdScreenInBackStack = navController.backStack.any { backStack ->
            backStack.destination.route == NavRoute.NavThirdScreen.route
        }
        Truth.assertThat(isThirdScreenInBackStack).isEqualTo(false)
    }
}