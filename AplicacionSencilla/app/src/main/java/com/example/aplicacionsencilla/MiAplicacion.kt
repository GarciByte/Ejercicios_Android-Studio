package com.example.aplicacionsencilla

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.aplicacionsencilla.ui.theme.AplicacionSencillaTheme

@Composable
fun MiAplicacion() {
    val context = LocalContext.current
    var texto by rememberSaveable { mutableStateOf(context.getString(R.string.txt_original)) }
    var estaPulsado by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = texto,
            modifier = Modifier.background(if (estaPulsado) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    texto = context.getString(R.string.txt_pulsado)
                    estaPulsado = true
                },
                enabled = !estaPulsado,
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    disabledContentColor = MaterialTheme.colorScheme.background
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = context.getString(R.string.btn_pulsar))
            }

            Button(
                onClick = {
                    texto = context.getString(R.string.txt_original)
                    estaPulsado = false
                },
                enabled = estaPulsado,
                colors = ButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.background,
                    disabledContainerColor = MaterialTheme.colorScheme.inversePrimary,
                    disabledContentColor = MaterialTheme.colorScheme.background
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = context.getString(R.string.btn_resetear))
            }
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO, name = "Modo Claro")
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, name = "Modo Oscuro")
@Composable
fun PreviewAppContentLight() {
    AplicacionSencillaTheme {
        MiAplicacion()
    }
}