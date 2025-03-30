package com.kyn.unitconverter


import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.kyn.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize())
                { innerPadding ->
                    UnitConverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {

        UnitConverter()

}


@Composable
fun UnitConverter(modifier: Modifier = Modifier){

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Red
    )

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }


    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value *100.0 / oConversionFactor.value).roundToInt() /100.0
        outputValue = result.toString()
    }
    Column (modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Unit Converter" , style=customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            label = {Text("Enter Value")}
        )
        Row {
                Box{
                    Button(onClick = { iExpanded = true}) {
                        Text(inputUnit)
                        Icon(Icons.Default.ArrowDropDown
                            , contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = iExpanded , onDismissRequest = { iExpanded = false} ) {
                        DropdownMenuItem(text ={ Text("Millimeters")}
                            , onClick={
                                iExpanded=false
                                inputUnit="Millimeters"
                                conversionFactor.value= 0.001
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Centimeters")}
                            , onClick={
                                iExpanded=false
                                inputUnit="Meters"
                                conversionFactor.value= 0.01
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Meters")}
                            , onClick={
                                iExpanded=false
                                inputUnit="Meters"
                                conversionFactor.value= 1.0
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Kilometers")}
                            , onClick={
                                iExpanded=false
                                inputUnit="Kilometers"
                                conversionFactor.value= 1000.0
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Feet")}
                            , onClick={
                                iExpanded=false
                                inputUnit="Feet"
                                conversionFactor.value= 0.3048
                                convertUnit()})
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box{
                    Button(onClick = { oExpanded = true}) {
                        Text(outputUnit)
                        Icon(Icons.Default.ArrowDropDown
                            , contentDescription = "Arrow Down")
                    }
                    DropdownMenu(expanded = oExpanded , onDismissRequest = { oExpanded = false} ) {
                        DropdownMenuItem(text ={ Text("Millimeters")}
                            , onClick={
                                oExpanded=false
                                outputUnit="Millimeters"
                                oConversionFactor.value= 0.001
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Centimeters")}
                            , onClick={
                                oExpanded=false
                                outputUnit="Meters"
                                oConversionFactor.value= 0.01
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Meters")}
                            , onClick={
                                oExpanded=false
                                outputUnit="Meters"
                                oConversionFactor.value= 1.0
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Kilometers")}
                            , onClick={
                                oExpanded=false
                                outputUnit="Kilometers"
                                oConversionFactor.value= 1000.0
                                convertUnit()})
                        DropdownMenuItem(text ={ Text("Feet")}
                            , onClick={
                                oExpanded=false
                                outputUnit="Feet"
                                oConversionFactor.value= 0.3048
                                convertUnit()})
                    }
                    }
                }
        Text( "Results: ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.headlineMedium
        )
        }

    }
