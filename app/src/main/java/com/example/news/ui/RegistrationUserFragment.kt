package com.example.news.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.FragmentRegistrationUserBinding
import com.example.news.ui.viewModel.RoleModelViewModel
import com.google.android.material.snackbar.Snackbar


class RegistrationUserFragment : Fragment() {
    private var _binding: FragmentRegistrationUserBinding? = null
    private val binding: FragmentRegistrationUserBinding
        get() = _binding ?: throw RuntimeException(FRAGMENT_ERROR)

    private lateinit var viewModel: RoleModelViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[RoleModelViewModel::class.java]
        showLoginFragment()
        registrationNewUser()
    }

    private fun registrationNewUser() {
        binding.btRegistrationAccount.setOnClickListener {
            val email = binding.etRegistrationEmail.text.toString()
            val password = binding.etRegistrationPassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                viewModel.registrationNewUser(email, password)
                viewModel.getRegisterStatusLiveData().observe(viewLifecycleOwner) {
                    if (it == true) {
                        launchListTopNewsFragment()
                    } else {
                        showSnackBar(REGISTRATION_USER_DATA_ERROR)
                    }
                }
            } else {
                binding.etRegistrationEmail.error = "Проверьте данные"
                showSnackBar(REGISTRATION_EMPTY_DATA)
            }
        }
    }

    private fun showLoginFragment() {
        binding.tvRegistrationLaunchToLoginFragment.setOnClickListener {
            launchLoginUserFragment()
        }
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun launchListTopNewsFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, ListTopNewsFragment.newInstance())
            .commit()
    }


    private fun launchLoginUserFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            .replace(R.id.main_container, LoginUserFragment.newInstance())
            .commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FRAGMENT_ERROR = "LoginUserFragment is null"
        private const val REGISTRATION_USER_DATA_ERROR = "Change data"
        private const val REGISTRATION_EMPTY_DATA = "Not enough data"
        fun newInstance(): Fragment {
            return RegistrationUserFragment()
        }
    }

}