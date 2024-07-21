package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var conversionFactor = remember { mutableStateOf(0.01) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 30.sp,
        color = Color.Red
    )

    fun convertUnits() {

        if (inputUnit == "Centimeters" && outputUnit == "Meters") {
            conversionFactor.value = 0.01
        } else if (inputUnit == "Centimeters" && outputUnit == "Feet") {
            conversionFactor.value = 0.0328084
        } else if (inputUnit == "Centimeters" && outputUnit == "Millimeters") {
            conversionFactor.value = 10.0
        }else if (inputUnit == "Meters" && outputUnit == "Centimeters") {
            conversionFactor.value = 100.0
        } else if (inputUnit == "Meters" && outputUnit == "Feet") {
            conversionFactor.value = 3.28084
        } else if (inputUnit == "Meters" && outputUnit == "Millimeters") {
            conversionFactor.value = 1000.0
        } else if (inputUnit == "Feet" && outputUnit == "Centimeters") {
            conversionFactor.value = 30.48
        } else if (inputUnit == "Feet" && outputUnit == "Meters") {
            conversionFactor.value = 0.3048
        } else if (inputUnit == "Feet" && outputUnit == "Millimeters") {
            conversionFactor.value = 304.8
        } else if (inputUnit == "Millimeters" && outputUnit == "Centimeters") {
            conversionFactor.value = 0.1
        } else if (inputUnit == "Millimeters" && outputUnit == "Meters") {
            conversionFactor.value = 0.001
        } else if (inputUnit == "Millimeters" && outputUnit == "Feet") {
            conversionFactor.value = 0.00328084
        } else {
            conversionFactor.value = 1.0
        }

        // ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0) / 100.0
        outputValue = result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(text = "Unit Converter", style = customTextStyle)
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnits()},
            label = {Text(  "Enter value")}
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            // Input Box
            Box {
                // Input button
                Button(onClick = {
                    iExpanded = true
                }, ) {
                    Text(text = inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false } ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            inputUnit = "Centimeters"
                            iExpanded = false
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputUnit = "Meters"
                            iExpanded = false
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputUnit = "Feet"
                            iExpanded = false
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            inputUnit = "Millimeters"
                            iExpanded = false
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(1.dp))
            Icon(Icons.Default.ArrowForward, contentDescription = "Arrow Left")
            Spacer(modifier = Modifier.width(1.dp))
            // Output box
            Box {
                // Output button
                Button(onClick = {
                    oExpanded = true
                }) {
                    Text(text = outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            outputUnit = "Centimeters"
                            oExpanded = false
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            outputUnit = "Meters"
                            oExpanded = false
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            outputUnit = "Feet"
                            oExpanded = false
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            outputUnit = "Millimeters"
                            oExpanded = false
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Result: $outputValue",
            style = MaterialTheme.typography.headlineMedium
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Amdany version 1.0",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 10.sp,
                    color = Color.Red
                )
            )
        }
    }
    }




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun GreetingPreview() {
    UnitConverterTheme {
        Greeting("Android")
        Greeting("Android")
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}