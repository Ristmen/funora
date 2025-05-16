package com.pb.funora.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pb.funora.SplashActivity         // eğer SplashActivity yerine SplashScreen composable’ın varsa onu import et
import com.pb.funora.auth.ui.AuthActivity   // aynı şekilde ForgotPassword/EmailVerification için
import com.pb.funora.auth.ui.ForgotPasswordScreen
import com.pb.funora.auth.ui.EmailVerificationScreen
import com.pb.funora.chat.data.ChatMessage
import com.pb.funora.chat.ui.ChatScreen
import com.pb.funora.coin.data.CoinTransaction
import com.pb.funora.coin.ui.CoinBalanceScreen
import com.pb.funora.coin.ui.CoinHistoryScreen
import com.pb.funora.coin.ui.StoreScreen
import com.pb.funora.commonui.model.NotificationItem
import com.pb.funora.commonui.*
import com.pb.funora.commonui.model.AvatarOption
import com.pb.funora.core.model.User
import com.pb.funora.core.ui.*

@Composable
fun FunoraNavHost() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            // Eğer bir SplashScreen composable’ı varsa onu burada çağır
            // SplashScreen(navController)
            // Yoksa direkt activity başlat:
            LaunchedEffect(Unit) {
                /* Intent yönlendirmesi vs. */
            }
        }

        composable("home") {
            HomeScreen(navController)
        }

        composable("friends") {
            // FriendsScreen iki parametre alıyor: onInvite ve friends
            FriendsScreen(
                friends = listOf(/* örnek User nesneleri */),
                onInvite = { user -> /* davet et işlemi */ }
            )
        }

        composable("chat") {
            ChatScreen(
                messages = listOf<ChatMessage>(),
                onSendText = { text -> /* gönder */ },
                onRecord = { /* ses kaydet */ }
            )
        }

        composable("coin") {
            CoinBalanceScreen(
                balance = 0,
                onWatchAd = { /* reklam izle */ },
                navController = navController
            )
        }

        composable("coinHistory") {
            CoinHistoryScreen(
                history = listOf<CoinTransaction>(),
                onBack = { navController.popBackStack() }
            )
        }

        composable("store") {
            StoreScreen(
                products = listOf(),
                onPurchase = { productId -> /* satın al */ },
                navController = navController
            )
        }

        composable("profile") {
            ProfileScreen(navController)
        }

        composable("tables") {
            GameLobbyListScreen(
                gameName = "ReflexTrap",
                rooms = listOf(),    // örnek lobi listesi
                onJoin = { roomId, roomName -> /* odaya katıl */ },
                onCreate = { /* oda oluştur */ }
            )
        }

        composable("createRoom") {
            CreateRoomScreen(navController)
        }

        composable("invite") {
            InviteFriendsScreen(navController)
        }

        composable("coming") {
            ComingSoonScreen()
        }

        composable("settings") {
            SettingsScreen(navController)
        }

        composable("notifications") {
            NotificationsScreen(
                notifications = listOf<NotificationItem>(),
                onMarkAllRead = { /* hepsini işaretle */ },
                onDelete      = { id -> /* sil */ }
            )
        }

        composable("avatar") {
            AvatarCustomizationScreen(
                parts = mapOf<String, List<AvatarOption>>(),
                onOptionSelected = { category, option -> /* seçenek seçildi */ }
            )
        }

        composable("leaderboard") {
            LeaderboardScreen(navController)
        }

        composable("gameplay") {
            GamePlayScreen(navController)
        }

        composable("tournaments") {
            TournamentsScreen(navController)
        }

        composable("help") {
            HelpSupportScreen(navController)
        }

        composable("about") {
            AboutLegalScreen(
                version = "1.0.0",
                onOpenPolicy = { policyType -> /* politika aç */ }
            )
        }

        composable("forgot") {
            ForgotPasswordScreen(
                onSubmit = { email -> /* şifre sıfırlama isteği */ },
                onSendReset = { /* reset e-postası gönderildi */ }
            )
        }

        composable("verify") {
            EmailVerificationScreen(
                onVerify = { code -> /* doğrula */ },
                onBack   = { /* geri dön */ }
            )
        }
    }
}
