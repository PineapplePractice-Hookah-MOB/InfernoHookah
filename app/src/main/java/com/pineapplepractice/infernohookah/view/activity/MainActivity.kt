package com.pineapplepractice.infernohookah.view.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.pineapplepractice.infernohookah.App
import com.pineapplepractice.infernohookah.R
import com.pineapplepractice.infernohookah.databinding.ActivityMainBinding
import com.pineapplepractice.infernohookah.viewmodel.MainActivityViewModel
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Inject
    lateinit var vmFactory: MainActivityViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        App.instance.dagger.inject(this)

        mainActivityViewModel =
            ViewModelProvider(this, vmFactory)[MainActivityViewModel::class.java]

        startApp()

    }

    fun getMainActivityViewModel(): MainActivityViewModel {
        return mainActivityViewModel
    }

    fun visibleBottomNavigation()= with(binding) {
        bottomMenuNavigation.visibility = View.VISIBLE
        fab.visibility = View.VISIBLE
    }

    private fun startApp() {
//        navController = Navigation.findNavController(this, R.id.fragmentPlaceholder)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentPlaceholder) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomMenuNavigation.visibility = View.GONE
        binding.fab.visibility = View.GONE
    }
    // проверяем регистрацию, определяем точку входа
/*    private fun startApp() = with(binding) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentPlaceholder) as NavHostFragment
        navController = navHostFragment.navController
        // Проверяем, авторизован ли пользователь
        val isLoggedIn = checkUserLoggedIn()
        // Определяем точку начала навигации в зависимости от статуса авторизации
        val startDestination = if (isLoggedIn) {
           bottomMenuNavigation.visibility = View.VISIBLE
            R.id.homeFragment
        } else {
            bottomMenuNavigation.visibility = View.GONE
            fab.visibility = View.GONE
            R.id.authFragmentStep1
        }
        // Устанавливаем точку начала навигации
        navController.navigate(startDestination)
    }*/

    override fun onStart() {
        super.onStart()

        initBottomNavigationMenu()
    }

    //Подключаем Bottom Navigation
    private fun initBottomNavigationMenu() = with(binding) {
        val bottomNavigationView = bottomMenuNavigation
        val navController = findNavController(R.id.fragmentPlaceholder)
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemReselectedListener { item ->
            val reselectedDestinationId = item.itemId
            navController.popBackStack(reselectedDestinationId, inclusive = false)
        }
        val fab = fab
        fab.setOnClickListener {
            navController.navigate(R.id.reservationFragment)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    private fun checkUserLoggedIn(): Boolean {
        // Здесь необходимо реаоизовать логику проверки наличия токена в SharedPreferences
        // Возвращаем true, если пользователь авторизован, и false в противном случае.
//        return true
        return false
    }

/*    fun launchDetailsFragment(promotion: Promotions, direction: Int, poster: ImageView) {
        //Создаем "посылку"
        val bundle = Bundle()
        val extras: FragmentNavigator.Extras = FragmentNavigator.Extras.Builder()
            .addSharedElement(
                poster,
                poster.transitionName
            )
            .build()

        //Кладем наш фильм в "посылку"
        bundle.putParcelable(KEY_PROMOTIONS_DETAILS_FRAGMENT, promotion)

        //Кладем фрагмент с деталями в перменную
        val fragment = PromotionDetailsFragment()

        //Прикрепляем нашу "посылку" к фрагменту
        fragment.arguments = bundle

        //Запускаем фрагмент
        navController.navigate(direction, fragment.arguments, null, extras)
    }*/

    companion object {
//        const val KEY_PROMOTIONS_DETAILS_FRAGMENT = "promotion"
        const val TRANSITION_DURATION = 400L
        const val TRANSITION_DURATION_FAST = 150L
    }
}