package com.example.materialcompose.screens

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.materialcompose.models.MedicineModel
import com.example.materialcompose.ui.theme.poppins
import com.example.materialcompose.viewModel.AppViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(email: String, viewModel: AppViewModel) {

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val list = viewModel.medicineList()
        val openDialog = remember { mutableStateOf(false) }
        val itemClicked = remember { mutableStateOf(0) }
        val itemVisibility = remember { mutableStateOf(false) }
        if (openDialog.value) {
            Dialog(dialogState = openDialog.value, list[itemClicked.value], dismissRequest = {
                openDialog.value = false
            })
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            itemVisibility.value = true
        }

        Column(modifier = Modifier.padding(it)) {

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .padding(horizontal = 30.dp), style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontFamily = poppins,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ), text = "Hey $email"
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(align = Alignment.CenterHorizontally)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 30.dp, vertical = 5.dp),
                style = TextStyle(textAlign = TextAlign.Center, fontFamily = poppins),
                text = "Current time is ${findCurrentTime()}"
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            LazyColumn(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {

                itemsIndexed(items = list) { index, medicine ->
                    AnimatedVisibility(
                        visible = itemVisibility.value, exit = slideOutHorizontally(
                            animationSpec = TweenSpec(300, 0, FastOutLinearInEasing)
                        ), enter = slideInHorizontally(
                            animationSpec = TweenSpec(300, 0, FastOutLinearInEasing)
                        )
                    ) {

                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp, vertical = 20.dp)
                            .background(
                                color = medicine.color, shape = RoundedCornerShape(20.dp)
                            )
                            .clickable {
                                itemClicked.value = index
                                openDialog.value = true
                            }
                            .padding(horizontal = 20.dp, vertical = 10.dp)


                        ) {
                            Column {
                                Text(
                                    text = "Name: " + medicine.name, color = Color.Black
                                )
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(20.dp)
                                )
                                Text(
                                    text = "Dose: " + medicine.dose, color = Color.Black
                                )
                                Spacer(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(20.dp)
                                )
                                Text(
                                    text = "Strength: " + medicine.strength, color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
private fun findCurrentTime(): String {
    val cal = Calendar.getInstance()
    val sdf = SimpleDateFormat("hh:mm aa")
    return sdf.format(cal.time)
}

@Composable
fun Dialog(
    dialogState: Boolean, medicine: MedicineModel, dismissRequest: (dialogState: Boolean) -> Unit
) {
    if (dialogState) {
        Dialog(onDismissRequest = { dismissRequest(false) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                        color = Color.White, shape = RoundedCornerShape(20.dp)
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Text(
                        text = "Details",
                        fontFamily = poppins,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Text(
                        text = "Name: ${medicine.name}",
                        fontFamily = poppins,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Dose: ${medicine.dose}",
                        fontFamily = poppins,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Strength: ${medicine.strength}",
                        fontFamily = poppins,
                        fontSize = 13.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(30.dp))

                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(50.dp)
                        ), onClick = { dismissRequest(false) }) {
                        Text(
                            text = "Got It",
                            style = TextStyle(fontFamily = poppins),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

