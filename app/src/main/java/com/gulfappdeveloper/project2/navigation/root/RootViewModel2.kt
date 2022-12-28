package com.gulfappdeveloper.project2.navigation.root

import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootViewModel2
@Inject constructor(
    private val useCase: UseCase
) : RootViewModel(useCase = useCase) {


}