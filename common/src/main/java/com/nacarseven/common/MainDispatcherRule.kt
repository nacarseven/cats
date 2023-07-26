package com.nacarseven.common

//import kotlinx.coroutines.ExperimentalCoroutinesApi
//
//@ExperimentalCoroutinesApi
//class MainDispatcherRule(
//    val dispatcher: TestDispatcher = UnconfinedTestDispatcher(),
//) : TestWatcher() {
//
//    override fun starting(description: Description) {
//        Dispatchers.setMain(dispatcher)
//    }
//
//    override fun finished(description: Description) {
//        Dispatchers.resetMain()
//    }
//}