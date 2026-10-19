package com.phune.btapt3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MaterialTheme {
                AppBaiTap()
            }
        }
    }
}

@Composable
fun AppBaiTap() {
    var manHinh by rememberSaveable {
        mutableStateOf(1)
    }

    // Bấm Back hệ thống ở màn chi tiết thì về danh sách.
    BackHandler(enabled = manHinh != 1) {
        manHinh = 1
    }

    when (manHinh) {
        1 -> Bai1(
            onOpen = { soManHinh ->
                manHinh = soManHinh
            }
        )

        2 -> Bai2(onBack = { manHinh = 1 })
        3 -> Bai3(onBack = { manHinh = 1 })
        4 -> Bai4(onBack = { manHinh = 1 })
        5 -> Bai5(onBack = { manHinh = 1 })
    }
}

@Composable
fun KhungManHinh(
    title: String,
    onBack: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    Scaffold(
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imePadding()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    color = Color(0xFF2196F3),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                if (onBack != null) {
                    TextButton(
                        onClick = onBack,
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Text(
                            text = "‹",
                            fontSize = 32.sp,
                            color = Color(0xFF2196F3)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                content = content
            )
        }
    }
}