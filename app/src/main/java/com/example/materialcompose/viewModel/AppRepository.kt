package com.example.materialcompose.viewModel

import com.example.materialcompose.models.MedicineModel
import com.example.materialcompose.ui.theme.Pink80
import com.example.materialcompose.ui.theme.Purple80
import com.example.materialcompose.ui.theme.PurpleGrey80

class AppRepository {

    fun getMedicineList(): List<MedicineModel> {
        return listOf(
            MedicineModel(
                "Asprine",
                "10",
                "500 mg",
                Pink80
            ),
            MedicineModel(
                "Lisinopril",
                "16",
                "1000 mg",
                Purple80
            ),
            MedicineModel(
                "Amoxicillin",
                "12",
                "500 mg",
                PurpleGrey80
            )
        )
    }
}