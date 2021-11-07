package com.odougle.callapimvvm

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.odougle.callapimvvm.ui.theme.CallApiMVVMTheme
import com.odougle.callapimvvm.ui.theme.Purple500
import com.odougle.callapimvvm.utils.Resource
import com.odougle.callapimvvm.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CallApiMVVMTheme {
                CallApi()
            }
        }
    }
}

@Composable
fun CallApi(
    viewModel: UserViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    val getAllUserData = viewModel.getUserData.observeAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Purple500)
                        .padding(15.dp)
                ) {
                    Text(
                        text = "User live Data",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                }

                scope.launch {
                    val result =  viewModel.getUserData()
                    if(result is Resource.Sucess){
                        Toast.makeText(context, "Fetching data success", Toast.LENGTH_SHORT).show()

                    }else if(result is Resource.Error){
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }

                if(!viewModel.isLoading.value){
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                if(viewModel.getUserData.value!!.isNotEmpty()){
                    LazyColumn(
                        modifier = Modifier.padding(10.dp)
                    ){
                        items(getAllUserData.value!!.size){ index ->
                            UserListItem(getAllUserData.value!![index])
                        }
                    }
                }
            }


        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    CallApiMVVMTheme {
        CallApi()
    }
}