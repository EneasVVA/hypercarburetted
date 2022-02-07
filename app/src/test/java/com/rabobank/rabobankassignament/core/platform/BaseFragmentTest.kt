package com.rabobank.rabobankassignament.core.platform

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.rabobank.rabobankassignament.AndroidTest
import com.rabobank.rabobankassignament.Loadable
import com.rabobank.rabobankassignament.databinding.FragmentCsvChooserBinding
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

class BaseFragmentTest : AndroidTest() {
    private val baseFragment = spyk(Fragment())
    @MockK private lateinit var context: Context
    @MockK private lateinit var loaderActivity: MyFragmentActivity

    @Before
    fun setUp() {
        every { baseFragment.activity } returns loaderActivity
    }

    @Test
    fun `should set Loadable instance on attach`() {
        baseFragment.onAttach(context)
        assert(baseFragment.animationLoaderMock == loaderActivity)
    }

    @Test
    fun `should unset Loadable instance on detach`() {
        baseFragment.onDetach()
        assert(baseFragment.animationLoaderMock == null)
    }

    private abstract class MyFragmentActivity : FragmentActivity(), Loadable

    private class Fragment : BaseFragment<FragmentCsvChooserBinding>(FragmentCsvChooserBinding::inflate) {
        val animationLoaderMock get() = animationLoader
    }
}