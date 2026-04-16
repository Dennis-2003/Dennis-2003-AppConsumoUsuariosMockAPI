# Documentación Técnica - UsuariosApi 📝

Esta aplicación consume una API REST de MockAPI para mostrar una lista de usuarios utilizando tecnologías modernas de Android.

## 1. Arquitectura de Paquetes
- `com.tuapp.usuariosapi.modelo`: Contiene la lógica de datos.
- `com.tuapp.usuariosapi.red`: Contiene la lógica de conexión a internet.
- `com.tuapp.usuariosapi.ui`: Contiene la lógica de visualización (Compose).

## 2. Componentes Clave

### 🔹 Modelo: `Usuario.kt`
Define cómo se interpretan los datos recibidos.
- **Importante**: Se usa `@SerializedName("Nombre")` con "N" mayúscula porque así lo envía el servidor. Si el servidor cambia a "name", este valor debe actualizarse.

### 🔹 Servicio: `ApiService.kt`
Es una interfaz que Retrofit usa para generar las peticiones.
- **Endpoint**: `usaruario-contrase-id`. Esta es la ruta final que se concatena a la URL base.

### 🔹 Cliente: `RetrofitClient.kt`
Configura Retrofit como un **Singleton** (una sola instancia para toda la app).
- Usa `GsonConverterFactory` para transformar automáticamente el texto JSON en objetos de la clase `Usuario`.

### 🔹 UI: `PantallaUsuarios.kt`
- **LaunchedEffect**: Dispara la petición de red asíncronamente al abrir la pantalla.
- **State management**: Utiliza variables de estado (`listaUsuarios`, `estaCargando`) para redibujar la pantalla automáticamente cuando llegan los datos.
- **Coil**: Se usa la función `AsyncImage` para descargar y mostrar fotos de perfil de forma eficiente.

## 3. Manejo de Errores
La aplicación captura excepciones (como falta de internet o error 404) y muestra un mensaje amigable al usuario en lugar de cerrarse bruscamente.
