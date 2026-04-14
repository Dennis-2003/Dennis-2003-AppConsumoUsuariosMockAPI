package com.tuapp.usuariosapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tuapp.usuariosapi.ui.PantallaUsuarios

/**
 * ACTIVITY PRINCIPAL: MainActivity
 *
 * Es el punto de entrada de la aplicación Android.
 * Su único trabajo aquí es inicializar Compose y
 * mostrar PantallaUsuarios como contenido raíz.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Permite que la UI se extienda hasta los bordes de la pantalla
        enableEdgeToEdge()

        // setContent reemplaza el XML de layouts tradicionales.
        // Todo lo que ponemos aquí es Jetpack Compose.
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding() // Evita que el contenido quede bajo la barra de estado
                ) {
                    // Mostramos nuestra pantalla principal
                    PantallaUsuarios()
                }
            }
        }
    }
}
