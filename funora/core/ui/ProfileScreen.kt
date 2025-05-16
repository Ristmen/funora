package com.pb.funora.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush // Degrade (Gradient) için
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight // Yazı tipi kalınlığı için
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.pb.funora.core.model.User // Bu model sınıfınızın projenizdeki yerine göre güncellenmelidir.
import com.pb.funora.core.viewmodel.ProfileViewModel // Bu ViewModel sınıfınızın projenizdeki yerine göre güncellenmelidir.
// Projenizdeki fontları import etmeniz gerekebilir. Örnek:
// import com.pb.funora.ui.theme.GoogleSans // veya Montserrat

@Composable
fun ProfileScreen(navController: NavController, userId: String) {
    val viewModel: ProfileViewModel = viewModel()
    val userState by viewModel.user.collectAsState()

    LaunchedEffect(key1 = userId) {
        viewModel.fetchUserData(userId)
    }

    // Proje temasına uygun degrade renkler
    val gradientBrush = remember {
        Brush.verticalGradient(
            colors = listOf(
                Color(0xFF673AB7), // Mor
                Color(0xFFFFEB3B), // Sarı
                Color(0xFF00BCD4)  // Turkuaz
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            // Arka planı degrade fırça ile değiştiriyoruz
            .background(brush = gradientBrush),
        contentAlignment = Alignment.Center
    ) {
        userState?.let { user ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp) // Kenarlara dolgu ekleyelim
            ) {
                // Kullanıcı avatarı. Etrafına beyaz bir halka ekleyebiliriz.
                Box(
                    modifier = Modifier
                        .size(120.dp) // Avatar boyutunu büyütelim
                        .background(Color.White.copy(alpha = 0.2f), CircleShape) // Hafif şeffaf beyaz halka
                        .padding(4.dp) // İç boşluk
                        .background(Color.Gray, CircleShape) // Avatar placeholder rengi
                    // Gerçek avatar görseli buraya Image Composable'ı ile eklenebilir.
                )

                // Kullanıcı adı soyadı. Yazı tipi ve kalınlığını güncelleyelim.
                Text(
                    text = "${user.firstName} ${user.lastName}",
                    fontSize = 28.sp, // Yazı boyutunu büyütelim
                    fontWeight = FontWeight.Bold, // Kalın yapalım
                    color = Color.White // Yazı rengini beyaz yapalım
                    // fontFamily = GoogleSans // Projenizdeki fontu kullanabilirsiniz
                )
                // Kullanıcı adı bilgisi.
                Text(
                    text = "@${user.username}", // Kullanıcı adının başına @ ekleyelim
                    fontSize = 18.sp,
                    color = Color.White.copy(alpha = 0.8f) // Hafif şeffaf beyaz
                    // fontFamily = GoogleSans
                )
                // Email bilgisi artık profil ekranında görünmeyebilir veya daha farklı bir yerde gösterilebilir.
                // Text(text = "Email: ${user.email}", fontSize = 16.sp, color = Color.White.copy(alpha = 0.8f))

                Spacer(modifier = Modifier.height(24.dp)) // Buton öncesi biraz boşluk

                // Profili Düzenle butonu. Daha modern bir tasarım verebiliriz.
                Button(
                    onClick = { navController.navigate("profile_edit/$userId") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White.copy(alpha = 0.9f), // Hafif şeffaf beyaz buton
                        contentColor = Color(0xFF673AB7) // Mor yazı rengi
                    ),
                    shape = CircleShape, // Yuvarlak kenarlı buton
                    modifier = Modifier.height(48.dp) // Buton yüksekliği
                ) {
                    Text(text = "Profili Düzenle")
                }

                // **Buraya kullanıcı istatistikleri, son oynanan oyunlar, rozetler gibi ek Composable'lar eklenecek.**
                // Örneğin, istatistikler için ayrı bir Card içine alınmış bir bölüm:
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("İstatistikler", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        // Burada istatistikler listelenecek
                        Text("Kazanç: X", fontSize = 16.sp)
                        Text("Kayıp: Y", fontSize = 16.sp)
                        Text("En Çok Oynanan: Oyun Z", fontSize = 16.sp)
                    }
                }
            }
        } ?: run {
            // Kullanıcı bilgisi yüklenirken gösterilen progress barı beyaz yapalım.
            CircularProgressIndicator(color = Color.White)
        }
    }
}