package com.pb.funora.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pb.funora.BuildConfig

// COMMONUI
import com.pb.funora.commonui.AboutLegalScreen
import com.pb.funora.commonui.AvatarCustomizationScreen
import com.pb.funora.commonui.ComingSoonScreen
import com.pb.funora.commonui.HelpSupportScreen
import com.pb.funora.commonui.LeaderboardScreen
import com.pb.funora.commonui.NotificationsScreen
import com.pb.funora.commonui.SettingsScreen
import com.pb.funora.commonui.model.NotificationItem

// CORE
import com.pb.funora.core.ui.CreateRoomScreen
import com.pb.funora.core.ui.GameLobbyListScreen
import com.pb.funora.core.ui.HomeScreen
import com.pb.funora.core.ui.InviteFriendsScreen
import com.pb.funora.core.ui.ProfileScreen
import com.pb.funora.core.ui.SplashScreen
import com.pb.funora.core.viewmodel.FriendsViewModel

// CHAT
import com.pb.funora.chat.ui.ChatScreen
import com.pb.funora.chat.viewmodel.ChatViewModel

// COIN
import com.pb.funora.coin.ui.CoinBalanceScreen
import com.pb.funora.coin.ui.CoinHistoryScreen
import com.pb.funora.coin.ui.StoreScreen
import com.pb.funora.coin.viewmodel.CoinViewModel
import com.pb.funora.coin.viewmodel.StoreViewModel

// GAMES
import com.pb.funora.games.ui.GamePlayScreen
import com.pb.funora.games.ui.TournamentsScreen

// AUTH
import com.pb.funora.auth.ui.EmailVerificationScreen
import com.pb.funora.auth.ui.ForgotPasswordScreen
import com.pb.funora.auth.viewmodel.AuthViewModel

@Composable
fun FunoraNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(navController = navController)
        }

        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("friends") {
            val vm: FriendsViewModel = hiltViewModel()
            InviteFriendsScreen(
                friends = vm.friends,
                onInvite = { user -> vm.invite(user) }
            )
        }

        composable("chat") {
            val vm: ChatViewModel = hiltViewModel()
            ChatScreen(
                messages = vm.messages,
                onSendText = { text -> vm.sendMessage(text) },
                onRecord = { vm.recordVoice() }
            )
        }

        composable("coin") {
            val vm: CoinViewModel = hiltViewModel()
            CoinBalanceScreen(
                balance = vm.balance,
                onWatchAd = { vm.watchAd() },
                onBack = { navController.popBackStack() }
            )
        }

        composable("coinHistory") {
            val vm: CoinViewModel = hiltViewModel()
            CoinHistoryScreen(
                history = vm.history,
                onBack = { navController.popBackStack() }
            )
        }

        composable("store") {
            val vm: StoreViewModel = hiltViewModel()
            StoreScreen(
                products = vm.products,
                onPurchase = { product -> vm.purchase(product) },
                onBack = { navController.popBackStack() }
            )
        }

        composable("profile") {
            ProfileScreen(navController = navController)
        }

        composable("lobbies") {
            GameLobbyListScreen(navController = navController)
        }

        composable("createRoom") {
            CreateRoomScreen(navController = navController)
        }

        composable("comingSoon") {
            ComingSoonScreen()
        }

        composable("settings") {
            SettingsScreen(navController = navController)
        }

        composable("notifications") {
            NotificationsScreen(
                items = emptyList<NotificationItem>(),
                onMarkAllRead = {},
                onDelete = {}
            )
        }

        composable("avatar") {
            AvatarCustomizationScreen(
                parts = emptyMap(),
                onOptionSelected = { _, _ -> }
            )
        }

        composable("leaderboard") {
            LeaderboardScreen(navController = navController)
        }

        composable("gameplay") {
            GamePlayScreen(navController = navController)
        }

        composable("tournaments") {
            TournamentsScreen(navController = navController)
        }

        composable("about") {
            AboutLegalScreen(
                version = BuildConfig.VERSION_NAME,
                onOpenPolicy = {}
            )
        }

        composable("forgotPassword") {
            val vm: AuthViewModel = hiltViewModel()
            ForgotPasswordScreen(
                onSubmit = { email -> vm.sendReset(email) },
                onSendReset = {}
            )
        }

        composable("verifyEmail") {
            val vm: AuthViewModel = hiltViewModel()
            EmailVerificationScreen(
                onVerify = { code -> vm.verify(code) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
