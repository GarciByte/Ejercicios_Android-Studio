package com.david.corrutinas

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.david.corrutinas.ui.theme.CorrutinasTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ConCorrutinas() {
    var barraProgreso by rememberSaveable { mutableStateOf(false) }
    var contador by remember { mutableIntStateOf(0) }

    var downloadProgress by remember { mutableFloatStateOf(0f) }
    val animatedDownloadProgress by animateFloatAsState(
        targetValue = downloadProgress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec,
        label = "change progress"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Contador: $contador")
        Button(
            onClick = { contador++ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = "Incrementar contador")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!barraProgreso) {
                    barraProgreso = true
                    CoroutineScope(Dispatchers.Default).launch {
                        while (downloadProgress < 1f) {
                            for (i in 1..10_000_000) {
                                if (i % 1_000_000 == 0) {
                                    downloadProgress += 0.0001f
                                    Log.i(
                                        "Progreso",
                                        "Progreso actual: $downloadProgress en hilo: ${Thread.currentThread().name}"
                                    )
                                }
                            }
                        }
                        downloadProgress = 0f
                        barraProgreso = false
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(text = if (barraProgreso) "Descarga en curso..." else "Iniciar descarga")
        }

        if (barraProgreso) {
            CircularProgressIndicator()
            LinearProgressIndicator(progress = { animatedDownloadProgress })
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewConCorrutinas() {
    CorrutinasTheme {
        ConCorrutinas()
    }
}