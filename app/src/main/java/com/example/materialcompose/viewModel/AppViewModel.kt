package com.example.materialcompose.viewModel

import androidx.lifecycle.ViewModel
import com.example.materialcompose.models.MedicineModel
import com.example.materialcompose.ui.theme.Pink80
import com.example.materialcompose.ui.theme.Purple80
import com.example.materialcompose.ui.theme.PurpleGrey80
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repo: AppRepository) : ViewModel() {

    fun medicineList() = repo.getMedicineList()

}