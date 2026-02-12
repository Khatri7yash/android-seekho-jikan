package com.skhojkn.seekhojikan.presentation.screens.paymentmodes

import android.widget.RadioGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.presentation.common.BaseColumn
import com.skhojkn.seekhojikan.presentation.common.BaseScreen
import com.skhojkn.seekhojikan.presentation.navigation.Screen
import com.skhojkn.seekhojikan.presentation.utils.annotation.ThemePreview


@Composable
fun PaymentModesScreen(
    viewModel: PaymentViewModel = hiltViewModel<PaymentViewModel>(),
    navigation: (Screen?, Array<out Any>?) -> Unit
) {
    val state by viewModel.paymentList.collectAsState()
    var paymentMethodsList by remember { mutableStateOf(listOf<String>()) }

    LaunchedEffect(Unit) {
        viewModel.getPaymentModes()
    }

    LaunchedEffect(state) {
        if (state is Result.Success) {
            paymentMethodsList = (state as Result.Success<List<String>>).data
        }
    }

    BaseScreen(title = Screen.PaymentModes.title, navigation = navigation) {
        BaseColumn(uiState = state) {
            PaymentOptions(paymentMethodsList)
        }
    }
}


@Composable
private fun PaymentOptions(paymentMethodsList: List<String>) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf<String?>(null) }
    Column(Modifier.selectableGroup()) {
        paymentMethodsList.forEach {
            Row(
                Modifier.fillMaxWidth()
                    .height(50.dp)
                    .selectable(
                        selected = (it == selectedOption),
                        onClick = { onOptionSelected(it) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (it == selectedOption),
                    onClick = null // null recommended for accessibility with screen readers
                )
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@ThemePreview
@Composable
private fun Preview() {
    PaymentModesScreen { _, _ -> }
}