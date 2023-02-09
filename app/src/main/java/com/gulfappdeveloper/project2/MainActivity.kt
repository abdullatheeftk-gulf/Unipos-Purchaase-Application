package com.gulfappdeveloper.project2

import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.gulfappdeveloper.project2.navigation.root.RootNavGraph
import com.gulfappdeveloper.project2.navigation.root.RootViewModel
import com.gulfappdeveloper.project2.presentation.ui_util.ScanFrom
import com.gulfappdeveloper.project2.ui.theme.Project2Theme
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootViewModel by viewModels<RootViewModel>()
        var scanFrom: ScanFrom = ScanFrom.PURCHASE_SCREEN

        val deviceId = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

        Log.e(TAG, "onCreate: ${BuildConfig.DEBUG}", )
        setContent {

            Project2Theme {

                val launcher =
                    rememberLauncherForActivityResult(contract = ScanContract(), onResult = {
                        if (it.contents != null) {
                            try {
                               // Log.w(TAG, "onCreate: ${it.contents}")
                                Toast.makeText(this, it.contents, Toast.LENGTH_LONG).show()
                                it.contents?.let { value ->
                                    when (scanFrom) {
                                        ScanFrom.PURCHASE_SCREEN -> {
                                            rootViewModel.searchProductByQrCode(value)
                                        }
                                        ScanFrom.STOCK_ADJUSTMENT_SCREEN -> {
                                            rootViewModel.searchProductByQrCodeForStockAdjustment(value = value)
                                        }
                                        ScanFrom.ADD_PRODUCT_SCREEN -> {
                                            rootViewModel.setAddProductBarcodeScanned(value)
                                        }
                                        ScanFrom.MULTI_UNIT_SCREEN -> {
                                           rootViewModel.setMultiUnitBarcodeScanned(value)
                                        }
                                        ScanFrom.PRICE_ADJUSTMENT_SCREEN->{
                                            rootViewModel.searchProductByQrCodeForPriceAdjustment(value = value)
                                        }
                                    }

                                }
                            } catch (e: Exception) {
                                // Log.e(TAG, "onCreate: ${e.message}", )
                                Toast.makeText(this, "Error in Scanning", Toast.LENGTH_LONG).show()
                            }

                        }
                    })


                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navHostController = rememberNavController()

                    RootNavGraph(
                        navHostController = navHostController,
                        hideKeyboard = {
                            hideSoftKeyboard()
                        },
                        onScanButtonClicked = { scan ->
                            scanFrom = scan
                            val scanOption = ScanOptions().apply {
                                setPrompt("Scan for the Barcode")
                                setBeepEnabled(true)
                                setOrientationLocked(true)
                                captureActivity = CaptureAct::class.java
                            }

                            launcher.launch(scanOption)
                        },
                        rootViewModel = rootViewModel,
                        deviceId = deviceId
                    )

                }
            }


        }

    }

    private fun hideSoftKeyboard() {
        this.currentFocus?.let { view ->
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /*override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.e(TAG, "onKeyDown: ", )
        return super.onKeyDown(keyCode, event)
    }*/
}

class CaptureAct : CaptureActivity() {

}

