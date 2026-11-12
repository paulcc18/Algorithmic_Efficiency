# Duplicate Methods: comparación experimental de algoritmos

Trabajo de **Algoritmos · Semana 2** ("Reto de clase: tabla experimental").

Se comparan dos algoritmos que trabajan sobre un arreglo de enteros para encontrar información sobre valores repetidos. Se mide cuántas **operaciones** realiza cada uno y cuánto **tiempo** tarda.

## Algoritmos

| Algoritmo | Idea | Resultado | Complejidad |
|---|---|---|---|
| **A** | Ordena una copia del arreglo (burbuja, ascendente o descendente) y compara cada elemento con su vecino anterior. | Cantidad de posiciones repetidas | O(n²) |
| **B** | Cuenta las apariciones de cada valor en un arreglo de frecuencias y busca la mayor. | La **moda** (valor que más se repite) y cuántas veces aparece | O(n) |

El código usa solo arreglos y ciclos, sin `HashMap`, `Arrays.sort` ni otras funciones que automaticen el trabajo.

## Archivos

- `duplicateMethods.java`: programa que ejecuta ambos algoritmos y muestra la tabla.
- `Comparacion_algoritmos.xlsx`: tabla en Excel con la comparación (hoja "Comparación") y el formato de la slide (hoja "Tabla experimental").

## Cómo ejecutarlo

Requiere tener instalado el JDK.

```bash
javac duplicateMethods.java
java duplicateMethods
```

Para probar el orden descendente, cambia `boolean upward = true;` a `false`.

## Metodología

- **Tamaños de prueba:** n = 100 y n = 1.000.
- **Datos:** arreglo de enteros aleatorios entre 0 y n-1, generado con `new Random(42)`. La semilla fija permite repetir el experimento con los mismos datos.
- **Repeticiones:** cada medición se repite **3 veces** y se reporta el **promedio**, como pide la condición del reto.
- **Tiempo:** medido con `System.nanoTime()` y expresado en milisegundos.
- **Operaciones contadas:**
  - **A:** comparaciones del ordenamiento burbuja + comparaciones con el vecino.
  - **B:** un conteo por cada elemento + comparaciones al buscar la frecuencia máxima.

## Resultados

| Algoritmo | n | operaciones | tiempo (ms) |
|---|---|---|---|
| A | 100 | 5.049 | 0,209 |
| A | 1.000 | 500.499 | 5,360 |
| B | 100 | 199 | 0,003 |
| B | 1.000 | 1.999 | 0,008 |

Salida adicional del programa con la semilla 42:

- n = 100: A encuentra 37 posiciones repetidas; B indica que la moda es 93 (aparece 5 veces).
- n = 1.000: A encuentra 378 posiciones repetidas; B indica que la moda es 391 (aparece 5 veces).

> Las operaciones son siempre las mismas, pero los **tiempos varían** entre ejecuciones y equipos. Los valores de la tabla provienen de una ejecución de ejemplo.

## Conclusión

El algoritmo B es mucho más eficiente que el A. Al pasar de n = 100 a n = 1.000, las operaciones de A se multiplican por unas 99 veces (comportamiento O(n²)), mientras que las de B se multiplican por unas 10 (comportamiento O(n)). Con n = 1.000, A realiza unas 250 veces más operaciones que B y resulta más de 600 veces más lento en tiempo.

El costo de B es que es menos general: su arreglo de frecuencias solo funciona si los valores están entre 0 y n-1, y no deja el arreglo ordenado. Por eso, si solo se necesita saber qué valor se repite más, B es la mejor opción. Si se necesita además el arreglo ordenado, A sigue siendo útil, preferiblemente con un algoritmo de ordenamiento más eficiente que la burbuja.

## Limitaciones

- Con ordenamiento burbuja, tamaños como n = 1.000.000 tardarían demasiado, por eso solo se probó con 100 y 1.000.
- Con tamaños pequeños, el tiempo se mide en microsegundos y se ve afectado por el calentamiento de la JVM y el sistema operativo.
