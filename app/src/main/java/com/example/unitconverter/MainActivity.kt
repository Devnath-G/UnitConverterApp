package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
                }
            }
        }
    }
}


@Composable
fun UnitConverter(){

    var InputValue by remember { mutableStateOf("")}
    var OutputValue by remember { mutableStateOf("")}
    var InputUnit by remember { mutableStateOf("Meter") }
    var OutputUnit by remember { mutableStateOf("Meter")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExpanded by remember { mutableStateOf(false)}
    val conversion = remember { mutableStateOf(1.00)}
    val conversionOut = remember { mutableStateOf(1.00)}

    val CustomText = TextStyle(fontFamily = FontFamily.Monospace, fontSize = 30.sp)

    fun Conversion(){
        val InputValueDouble = InputValue.toDoubleOrNull() ?: 0.0
        val result = (InputValueDouble * conversion.value * 100.0 / conversionOut.value).roundToInt() / 100.0
        OutputValue = result.toString()
    }





    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Unit Converter", style = CustomText)
        Spacer(modifier = Modifier.height(35.dp))
        OutlinedTextField(value = InputValue, onValueChange = {
            InputValue = it
            Conversion()}, label = { Text(text = "Enter Value")}, singleLine = true )
        Spacer(modifier = Modifier.height(25.dp))

        Row {
            // Input Box
            Box {
                // Input Button
                Button(onClick = {iExpanded = true}) {
                    Text(text = InputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded , onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        iExpanded = false
                        InputUnit = "Centimeter"
                        conversion.value = 0.01
                        Conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter")}, onClick = {
                        iExpanded = false
                        InputUnit = "Meter"
                        conversion.value = 1.0
                        Conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        iExpanded = false
                        InputUnit = "Feet"
                        conversion.value = 0.3048
                        Conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter")}, onClick = {
                        iExpanded = false
                        InputUnit = "Millimeter"
                        conversion.value = 0.001
                        Conversion()
                    })
                    
                }
            }
            Spacer(modifier = Modifier.width(55.dp))
            // Output Box
            Box {
                // Output Button
                Button(onClick = { oExpanded = true}) {
                    Text(text = OutputUnit)
                    Icon(Icons.Default.ArrowDropDown,contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter") }, onClick = {
                        oExpanded = false
                        OutputUnit = "Centimeter"
                        conversionOut.value = 0.01
                        Conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Meter") }, onClick = {
                        oExpanded = false
                        OutputUnit = "Meter"
                        conversionOut.value = 1.00
                        Conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        OutputUnit = "Feet"
                        conversionOut.value = 0.3048
                        Conversion()
                    })
                    DropdownMenuItem(text = { Text(text = "Millimeter") }, onClick = {
                        oExpanded = false
                        OutputUnit = "Millimeter"
                        conversionOut.value = 0.001
                        Conversion()
                    })
                }

            }

        }
        Spacer(modifier = Modifier.height(45.dp))
        Text("Result : $OutputValue $OutputUnit ")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}